/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.Settings;
import baritone.api.event.listener.IEventBus;
import baritone.api.utils.BlockOptionalMeta;
import baritone.api.utils.IPlayerContext;
import baritone.behavior.Behavior;
import baritone.behavior.InventoryBehavior;
import baritone.behavior.LookBehavior;
import baritone.behavior.PathingBehavior;
import baritone.cache.WorldProvider;
import baritone.command.manager.CommandManager;
import baritone.event.GameEventHandler;
import baritone.event.SpigotGameEventHandlerWrapper;
import baritone.process.*;
import baritone.selection.SelectionManager;
import baritone.utils.BlockStateInterface;
import baritone.utils.InputOverrideHandler;
import baritone.utils.PathingControlManager;
import baritone.utils.player.PrimaryPlayerContext;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.block.Block;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Brady
 * @since 7/31/2018
 */
public class Baritone implements IBaritone {

    private static ThreadPoolExecutor threadPool;
//    private static File dir;

    static {
        threadPool = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());

//        dir = new File(Minecraft.getInstance().gameDirectory, "baritone");
//        if (!Files.exists(dir.toPath())) {
//            try {
//                Files.createDirectories(dir.toPath());
//            } catch (IOException ignored) {}
//        }
    }

    private GameEventHandler gameEventHandler;

    private PathingBehavior pathingBehavior;
    private LookBehavior lookBehavior;
    private InventoryBehavior inventoryBehavior;
    private InputOverrideHandler inputOverrideHandler;

    private FollowProcess followProcess;
    private MineProcess mineProcess;
    private GetToBlockProcess getToBlockProcess;
    private CustomGoalProcess customGoalProcess;
//    private BuilderProcess builderProcess;
    private ExploreProcess exploreProcess;
    private BackfillProcess backfillProcess;
    private FarmProcess farmProcess;

    private PathingControlManager pathingControlManager;
    private SelectionManager selectionManager;
    private CommandManager commandManager;

    private IPlayerContext playerContext;
    private WorldProvider worldProvider;

    public SpigotGameEventHandlerWrapper spigotGameEventHandlerWrapper;

    public BlockStateInterface bsi;

    public Baritone(PathfinderMob entity) {


            this.gameEventHandler = new GameEventHandler(this);
            this.spigotGameEventHandlerWrapper = new SpigotGameEventHandlerWrapper(gameEventHandler);

            // Define this before behaviors try and get it, or else it will be null and the builds will fail!
            this.worldProvider = new WorldProvider();
            this.playerContext = new PrimaryPlayerContext(this, entity);

            {
                // the Behavior constructor calls baritone.registerBehavior(this) so this populates the behaviors arraylist
                pathingBehavior = new PathingBehavior(this);
                lookBehavior = new LookBehavior(this);
                inventoryBehavior = new InventoryBehavior(this);
                inputOverrideHandler = new InputOverrideHandler(this);
            }

            this.pathingControlManager = new PathingControlManager(this);
            {
                this.pathingControlManager.registerProcess(followProcess = new FollowProcess(this));
                this.pathingControlManager.registerProcess(mineProcess = new MineProcess(this));
                this.pathingControlManager.registerProcess(customGoalProcess = new CustomGoalProcess(this)); // very high iq
                this.pathingControlManager.registerProcess(getToBlockProcess = new GetToBlockProcess(this));
//                this.pathingControlManager.registerProcess(builderProcess = new BuilderProcess(this));
                this.pathingControlManager.registerProcess(exploreProcess = new ExploreProcess(this));
                this.pathingControlManager.registerProcess(backfillProcess = new BackfillProcess(this));
                this.pathingControlManager.registerProcess(farmProcess = new FarmProcess(this));
            }


            this.selectionManager = new SelectionManager(this);
            this.commandManager = new CommandManager(this);
            BaritoneAPI.allBaritones.add(this);
    }

    @Override
    public PathingControlManager getPathingControlManager() {
        return this.pathingControlManager;
    }

    public void registerBehavior(Behavior behavior) {
        this.gameEventHandler.registerEventListener(behavior);
    }

    @Override
    public InputOverrideHandler getInputOverrideHandler() {
        return this.inputOverrideHandler;
    }

    @Override
    public CustomGoalProcess getCustomGoalProcess() {
        return this.customGoalProcess;
    }

    public void startGetToBlock(Block block){
        this.getGetToBlockProcess().getToBlock(new BlockOptionalMeta(block));
    }

    @Override
    public GetToBlockProcess getGetToBlockProcess() {
        return this.getToBlockProcess;
    }

    @Override
    public IPlayerContext getPlayerContext() {
        return this.playerContext;
    }

    @Override
    public FollowProcess getFollowProcess() {
        return this.followProcess;
    }

    @Override
    public BuilderProcess getBuilderProcess() {
//        return this.builderProcess;
        return null;
    }

    public InventoryBehavior getInventoryBehavior() {
        return this.inventoryBehavior;
    }

    @Override
    public LookBehavior getLookBehavior() {
        return this.lookBehavior;
    }

    public ExploreProcess getExploreProcess() {
        return this.exploreProcess;
    }

    @Override
    public MineProcess getMineProcess() {
        return this.mineProcess;
    }

    public FarmProcess getFarmProcess() {
        return this.farmProcess;
    }

    @Override
    public PathingBehavior getPathingBehavior() {
        return this.pathingBehavior;
    }

    @Override
    public SelectionManager getSelectionManager() {
        return selectionManager;
    }

    @Override
    public WorldProvider getWorldProvider() {
        return this.worldProvider;
    }

    @Override
    public IEventBus getGameEventHandler() {
        return this.gameEventHandler;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }


    public static Settings settings() {
        return BaritoneAPI.getSettings();
    }

    public static File getDir() {
        return null;
    }

    public static Executor getExecutor() {
        return threadPool;
    }
}
