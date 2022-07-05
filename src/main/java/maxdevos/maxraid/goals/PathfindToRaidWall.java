package maxdevos.maxraid.goals;

import maxdevos.maxraid.base.AttackPoint;
import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.VecUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class PathfindToRaidWall extends Goal {

    PathfinderMob mob;
    MaxRaid raid;
    protected BlockPos goalPos;
    private Vec3 lastPos;
    public int stuckTicks = 0;

    public PathfindToRaidWall(PathfinderMob m, MaxRaid raid){
        mob = m;
        this.raid = raid;
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick(){
        if(!mob.getNavigation().isInProgress()){
            AttackPoint nearestPos = raid.raidBase.getNearestWallAttackPoint(VecUtil.vec3ToBPos(mob.position()));


//            System.out.println("STARTING POS: " + mob.position() + "       NAVIGATING TO NEAREST ATTACK POINT: " + nearestPos);
            goalPos = nearestPos.getBaseBlockPos();
            Path path = mob.getNavigation().createPath(goalPos, 1, 1);
            mob.getNavigation().moveTo(path, 2.0);

        }
        else if(mob.getNavigation().isStuck()){
            Vec3 var1 = DefaultRandomPos.getPosTowards(this.mob, 60, 20, Vec3.atBottomCenterOf(goalPos), 1.5707963705062866);
            if (var1 != null) {
                this.mob.getNavigation().moveTo(var1.x, var1.y, var1.z, 2.0);
            }
        }

        if(stuckTicks > 40){
            AttackPoint nearestPos = raid.raidBase.getNearestWallAttackPoint(VecUtil.vec3ToBPos(mob.position().add(new Vec3(Math.random()*25, 0, Math.random()*25))));
            goalPos = nearestPos.getBaseBlockPos();
            Path path = mob.getNavigation().createPath(goalPos, 1, 1);
            mob.getNavigation().moveTo(path, 2.0);
        }

        if(mob.getNavigation().isStuck() || (lastPos != null && lastPos.distanceTo(mob.position()) < 0.1)){
            stuckTicks++;
        }
        else{
            stuckTicks = 0;
        }

//        if(stuckTicks > 40){
//            doStuck();
//        }

        lastPos = mob.position();
    }

    public void doStuck(){
    }
}
