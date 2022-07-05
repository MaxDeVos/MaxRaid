package maxdevos.maxraid.goals;

import maxdevos.maxraid.util.VecUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.bukkit.util.BlockVector;

public class SpiderMoveTowardsPointGoal extends Goal {

    Spider spider;
    double speed;
    Vec3 goalPos;

    public SpiderMoveTowardsPointGoal(Spider entityspider, BlockVector pos, double speed) {
        spider = entityspider;
        this.speed = speed;
        this.goalPos = VecUtil.bVecToVec3(pos);
    }

    public boolean canUse() {
        return true;
    }


    public boolean canContinueToUse() {
        return !this.spider.getNavigation().isDone();
    }

    public void start() {
        Path p = ((WallClimberNavigation) spider.getNavigation()).createPath(new BlockPos(this.goalPos), 1, 1);
//        mob.getNavigation().moveTo(p, this.speedModifier);
    }

}