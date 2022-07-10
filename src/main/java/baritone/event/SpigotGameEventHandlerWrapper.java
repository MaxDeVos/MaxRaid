package baritone.event;

import baritone.api.event.events.*;
import baritone.api.event.events.ChunkEvent;
import baritone.api.event.events.WorldEvent;
import baritone.api.event.events.type.EventState;
import maxdevos.maxraid.RaidPlugin;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.world.*;
import org.bukkit.scheduler.BukkitRunnable;

public class SpigotGameEventHandlerWrapper implements Listener {

    private static int tickCount = 0;
    GameEventHandler gameEventHandler;

    private boolean active = true;
    private int taskID;

    public SpigotGameEventHandlerWrapper(GameEventHandler gameEventHandler){
        this.gameEventHandler = gameEventHandler;
        RaidPlugin.getInstance().getServer().getPluginManager().registerEvents(this, RaidPlugin.getInstance());

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(RaidPlugin.getInstance(), tickLoop, 1L, 1L);
        System.out.println("STARTED SCHEDULER OF ID " + taskID);
    }

    boolean flag = true;

    public void onTick(){
//        if(flag){
//            flag = false;
//        } else{
//            TickEvent event = new TickEvent(EventState.POST, TickEvent.Type.IN, tickCount);
//            gameEventHandler.onTick(event);
//        }
        TickEvent event = new TickEvent(EventState.PRE, TickEvent.Type.IN, tickCount);
        tickCount++;
        gameEventHandler.onTick(event);

    }

    public void playerMoveEvent(){
        PlayerUpdateEvent event = new PlayerUpdateEvent(EventState.PRE);
        gameEventHandler.onPlayerUpdate(event);
    }

    @EventHandler
    public void onWorldLoadEvent(WorldLoadEvent event){
        WorldEvent worldEvent = new WorldEvent(((CraftWorld)event.getWorld()).getHandle(), EventState.POST);
        gameEventHandler.onWorldEvent(worldEvent);
    }

    public void loadWorld(ServerLevel level){
        WorldEvent worldEvent = new WorldEvent(level, EventState.POST);
        gameEventHandler.onWorldEvent(worldEvent);
    }


    @EventHandler
    public void onWorldUnloadEvent(WorldUnloadEvent event){
        WorldEvent worldEvent = new WorldEvent(null, EventState.POST);
        gameEventHandler.onWorldEvent(worldEvent);
    }

    @EventHandler
    public void onChunkLoadEvent(ChunkLoadEvent event){
        ChunkEvent chunkEvent = new ChunkEvent(EventState.PRE, ChunkEvent.Type.LOAD, event.getChunk().getX(), event.getChunk().getZ());
        gameEventHandler.onChunkEvent(chunkEvent);
    }

    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent event){
        ChunkEvent chunkEvent = new ChunkEvent(EventState.PRE, ChunkEvent.Type.UNLOAD, event.getChunk().getX(), event.getChunk().getZ());
        gameEventHandler.onChunkEvent(chunkEvent);
    }

    @EventHandler
    public void onChunkPopulateEvent(ChunkPopulateEvent event){
        ChunkEvent chunkEvent = new ChunkEvent(EventState.PRE, ChunkEvent.Type.POPULATE_FULL, event.getChunk().getX(), event.getChunk().getZ());
        gameEventHandler.onChunkEvent(chunkEvent);
    }

//    @EventHandler
//    public void onPlayerMoveEvent(PlayerMoveEvent event) {
//        System.out.println("onPlayerMoveEvent():  " + event.getEventName());
//    }

//    @EventHandler
//    public void onBlockInteractEvent(BlockPhysicsEvent event) {
//        System.out.println("onBlockInteractEvent():  " + event.getEventName());
//    }

    @EventHandler
    public void onDeathEvent(EntityDeathEvent event) {
        gameEventHandler.onPlayerDeath();
//        System.out.println("CANCELLING LOOP:  " + taskID);
        Bukkit.getScheduler().cancelTask(taskID);
        active = false;
    }

    Runnable tickLoop = new BukkitRunnable() {
        @Override
        public void run() {
//            System.out.println("LOOP " + taskID + "  |  active = " + active);
//             "  |  isCancelled() = " + isCancelled()
            if(active){
                onTick();
                playerMoveEvent();
            } else{
                if(!isCancelled()){
                    cancel();
                }
//                active = true; // do this so that cancel() is only called once
            }
        }

        @Override
        public synchronized void cancel() throws IllegalStateException {
            super.cancel();
//            System.out.println("LOOP SUCCESSFULLY CANCELLED");
        }
    };

}
