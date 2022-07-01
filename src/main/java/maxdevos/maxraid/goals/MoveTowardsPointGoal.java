package maxdevos.maxraid.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.bukkit.util.BlockVector;

import java.util.EnumSet;

public class MoveTowardsPointGoal extends Goal {
    private final PathfinderMob mob;
    private final Vec3 goalPos;
    private final double speedModifier;

    private int repathTicker;


    public MoveTowardsPointGoal(PathfinderMob var0, BlockVector pos, double speedModifier) {
        this.mob = var0;
        this.speedModifier = speedModifier;
        this.goalPos = new Vec3(pos.getX(), pos.getY(), pos.getZ());
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean canUse() {
        return true;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick(){
        super.tick();
        if(!mob.isPathFinding() || repathTicker > 20){
            repathTicker = 0;
            if(mob.getTarget() == null){
                Path p = mob.getNavigation().createPath(new BlockPos(this.goalPos), 1, 1);
                mob.getNavigation().moveTo(p, this.speedModifier);
            }
            else if(mob.getLevel().getNearestPlayer(mob, 120).distanceTo(mob) < 15){
                mob.setTarget(mob.getLevel().getNearestPlayer(mob, 120));
            }
            else{
                Path p = mob.getNavigation().createPath(new BlockPos(mob.getTarget().position()), 1, 1);
                mob.getNavigation().moveTo(p, this.speedModifier);
            }
        }
        repathTicker++;
    }

    public void start(){
        repathTicker = 0;
    }

}
