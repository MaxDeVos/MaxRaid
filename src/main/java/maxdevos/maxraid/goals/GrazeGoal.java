package maxdevos.maxraid.goals;

import maxdevos.maxraid.util.VecTools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.Path;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.*;

public class GrazeGoal extends Goal {

    PathfinderMob mob;
    Queue<Block> targets = new LinkedList<>();
    Block currentTarget;

    public GrazeGoal(PathfinderMob mob){
        this.mob = mob;
    }


    @Override
    public boolean canUse() {
        return true;
    }

    private void getNextTarget(){
        currentTarget = targets.poll();
        if(currentTarget != null && currentTarget.getType().equals(Material.SWEET_BERRY_BUSH)){
            getNextTarget();
        }
    }

    @Override
    public void tick(){

        if(targets.size() > 0){
            if(mob.getBukkitEntity().getLocation().distance(currentTarget.getLocation()) < 2.0){
                currentTarget.breakNaturally();
                getNextTarget();
                Path p = mob.getNavigation().createPath(new BlockPos(VecTools.blockVectorToVec3(currentTarget.getLocation().toVector().toBlockVector())), 0, 0);
                mob.getNavigation().moveTo(p, 3f);
            }
        }
        else{
            int xBase = (int) Math.floor(mob.position().x);
            int zBase = (int) Math.floor(mob.position().z);

            ArrayList<Block> blocks = new ArrayList<>();

            for(int x = xBase-50; x < xBase+50; x++){
                for(int z = zBase-50; z < zBase+50; z++){
                    Block b = mob.getLevel().getWorld().getBlockAt(x, mob.getLevel().getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z) - 1, z);
                    if(b.getType().equals(Material.SWEET_BERRY_BUSH)){
                        blocks.add(b);
                    }
                }
            }

            Collections.sort(blocks, (o1, o2) -> {
                double dist1 = o1.getLocation().distance(mob.getBukkitEntity().getLocation());
                double dist2 = o2.getLocation().distance(mob.getBukkitEntity().getLocation());
                if (dist1 > dist2) {
                    return 1;
                } else if (dist1 == dist2) {
                    return 0;
                }
                return -1;
            });

            targets.addAll(blocks);
            currentTarget = targets.poll();

            Path p = mob.getNavigation().createPath(new BlockPos(VecTools.blockVectorToVec3(currentTarget.getLocation().toVector().toBlockVector())), 0, 0);
            mob.getNavigation().moveTo(p, 3f);
        }
    }


}
