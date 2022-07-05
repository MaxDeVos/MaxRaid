package maxdevos.maxraid.goals;

import maxdevos.maxraid.base.BlockCluster;
import maxdevos.maxraid.util.VecUtil;
import maxdevos.maxraid.util.WorldUtils;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.Path;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.BlockVector;

import java.util.*;

public class GrazeGoal extends Goal {

    PathfinderMob mob;
    LinkedList<Block> targets = new LinkedList<>();
    Block currentTarget;
    BlockCluster<Block> currentCluster;
    int cornerTimer = 0;
    int range;
    boolean killIfNoTargets;

    public GrazeGoal(PathfinderMob mob, int range, boolean killIfNoTargets) {
        this.mob = mob;
        this.range = range;
        this.killIfNoTargets = killIfNoTargets;
    }

    public GrazeGoal(PathfinderMob mob) {
        this(mob, 100, true);
    }

    @Override
    public boolean canUse() {
        if(killIfNoTargets){
            return true;
        }
        findTargets(range);
        return !targets.isEmpty();
    }

    private void getNextTarget() {

        Location newLoc = currentCluster.getNextCorner();
        if (newLoc != null) {
            currentTarget = newLoc.getBlock();
            cornerTimer = 0;
        } else if(!targets.isEmpty()){
            currentTarget = null;
            sortAndFilterTargets();
            currentCluster = WorldUtils.clusterize(targets.getFirst(), targets);
            if(!currentCluster.isEmpty()){
                getNextTarget();
            }
        } else{
            findTargets(range);
            if(targets.isEmpty() && killIfNoTargets){
                mob.kill();
            }
        }
    }

    @Override
    public void tick() {

        // If no target and a valid block exists in targets, set that as target
        if (currentTarget != null && currentCluster.size() > 0) {
            if (mob.position().distanceTo(VecUtil.locToVec3(currentTarget.getLocation())) <= 1) {
                getNextTarget();
            } else{
                cornerTimer++;
            }
            BlockVector wantedVec = currentTarget.getLocation().toVector().toBlockVector();
            Path p = mob.getNavigation().createPath(wantedVec.getX(), wantedVec.getY(), wantedVec.getZ(), 0);
            if(p == null || !p.canReach()){
                abandonTarget();
            }
            mob.getNavigation().moveTo(p, 2f);
        }
        // Otherwise rescan for valid targets
        else {
            findTargets(range);
            sortAndFilterTargets();
            currentCluster = WorldUtils.clusterize(targets.getFirst(), targets);
            getNextTarget();
        }

        if(cornerTimer > 100){
            abandonTarget();
        }
    }

    private void abandonTarget(){
        targets.remove(currentTarget);
        currentCluster.remove(currentTarget);
        getNextTarget();
    }

    private void findTargets(int range){
        int xBase = (int) Math.floor(mob.position().x);
        int zBase = (int) Math.floor(mob.position().z);

        LinkedList<Block> blocks = new LinkedList<>();

        for (int x = xBase - range; x < xBase + range; x++) {
            for (int z = zBase - range; z < zBase + range; z++) {
                Block b = mob.getLevel().getWorld().getBlockAt(x, mob.getLevel().getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z) - 1, z);
                blocks.add(b);
            }
        }
        targets.addAll(blocks);
    }

    private void sortAndFilterTargets() {

        LinkedList<Block> updatedBlocks = new LinkedList<>();
        for (Block b : targets) {
            if (WorldUtils.isBlockType(b, "minecraft:sweet_berry_bush")) {
                updatedBlocks.add(b);
            }
        }
        targets = updatedBlocks;

        targets.sort((o1, o2) -> {
            double dist1 = o1.getLocation().distance(mob.getBukkitEntity().getLocation());
            double dist2 = o2.getLocation().distance(mob.getBukkitEntity().getLocation());
            if (dist1 > dist2) {
                return 1;
            } else if (dist1 == dist2) {
                return 0;
            }
            return -1;
        });
    }

}