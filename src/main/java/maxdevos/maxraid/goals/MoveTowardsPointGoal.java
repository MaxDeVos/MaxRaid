package maxdevos.maxraid.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import org.bukkit.util.BlockVector;

import java.util.EnumSet;

public class MoveTowardsPointGoal extends Goal {
    private final PathfinderMob mob;
    private final Vec3 goalPos;
    private double wantedX;
    private double wantedY;
    private double wantedZ;
    private final double speedModifier;


    public MoveTowardsPointGoal(PathfinderMob var0, BlockVector pos, double speedModifier) {
        this.mob = var0;
        this.speedModifier = speedModifier;
        this.goalPos = new Vec3(pos.getX(), pos.getY(), pos.getZ());
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean canUse() {
        Vec3 var0 = DefaultRandomPos.getPosTowards(this.mob, 16, 7, this.goalPos, Math.PI * 0.5);
        if (var0 == null) {
            return false;
        } else {
            this.wantedX = var0.x;
            this.wantedY = var0.y;
            this.wantedZ = var0.z;
            return true;
        }
    }

    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone();
    }

    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }

}
