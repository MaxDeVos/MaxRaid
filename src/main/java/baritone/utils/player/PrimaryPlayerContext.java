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

import baritone.Baritone;
import baritone.api.cache.IWorldData;
import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerContext;
import baritone.api.utils.IPlayerController;
import baritone.api.utils.RayTraceUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.phys.HitResult;

/**
 * Implementation of {@link IPlayerContext} that provides information about the primary player.
 *
 * @author Brady
 * @since 11/12/2018
 */
public class PrimaryPlayerContext implements IPlayerContext, Helper {

    private PathfinderMob entity;
    private PlayerController playerController;
    private Baritone baritone;

    public PrimaryPlayerContext(Baritone baritone, PathfinderMob entity){
        this.entity = entity;
        this.playerController = new PlayerController(entity);
        this.baritone = baritone;
    }

    @Override
    public PathfinderMob mob() {
        return entity;
    }

    @Override
    public IPlayerController playerController() {
        return playerController;
    }

    @Override
    public ServerLevel world() {
        return (ServerLevel) entity.getLevel();
    }

    @Override
    public IWorldData worldData() {
        return baritone.getWorldProvider().getCurrentWorld();
    }

    @Override
    public HitResult objectMouseOver() {
        return RayTraceUtils.rayTraceTowards(mob(), playerRotations(), playerController().getBlockReachDistance());
    }
}
