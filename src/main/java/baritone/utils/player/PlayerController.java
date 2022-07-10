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

package baritone.utils.player;

import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerController;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Objects;


/**
 * Implementation of {@link IPlayerController} that chains to the primary player controller's methods
 *
 * @author Brady
 * @since 12/14/2018
 */
public class PlayerController implements IPlayerController, Helper {

    private PathfinderMob mob;
    public PlayerController(PathfinderMob mob){
        this.mob = mob;
    }

    @Override
    public void syncHeldItem() {
        // Since we ARE the server I don't think this is an issue
//        ((IPlayerControllerMP) mc.gameMode).callSyncCurrentPlayItem();
    }

    @Override
    public boolean hasBrokenBlock() {
//        return ((IPlayerControllerMP) mc.gameMode).getCurrentBlock().getY() == -1;
        return true; //TODO actually work out if the player has broken the block
    }

    @Override
    public boolean onPlayerDamageBlock(BlockPos pos, Direction side) {
//        return mc.gameMode.continueDestroyBlock(pos, side);
        return true; //TODO this probably won't work
    }

    @Override
    public void resetBlockRemoving() {
//        mc.gameMode.stopDestroyBlock();
        //TODO reset block removing? what does this even do
    }

    @Override
    public void windowClick(int windowId, int slotId, int mouseButton, ClickType type, Player player) {
//        mc.gameMode.handleInventoryMouseClick(windowId, slotId, mouseButton, type, player);
    }

    @Override
    public GameType getGameType() {
        return Objects.requireNonNull(mob.level.getServer()).getDefaultGameType();
    }

    @Override
    public InteractionResult processRightClickBlock(LivingEntity player, Level world, InteractionHand hand, BlockHitResult result) {
        // primaryplayercontroller is always in a ClientWorld so this is ok //TODO right click block
//        return mc.gameMode.useItemOn(player, (ServerLevel) world, hand, result);
        return null;
    }

    @Override
    public InteractionResult processRightClick(LivingEntity player, Level world, InteractionHand hand) {
//        return mc.gameMode.useItem(player, world, hand);
        return null; //TODO right click
    }

    @Override
    public boolean clickBlock(BlockPos loc, Direction face) {
//        return mc.gameMode.startDestroyBlock(loc, face);
        return mob.level.destroyBlock(loc, false, mob);
    }

    @Override
    public void setHittingBlock(boolean hittingBlock) {
//        ((IPlayerControllerMP) mc.gameMode).setIsHittingBlock(hittingBlock); //TODO fake mob hitting block
    }
}
