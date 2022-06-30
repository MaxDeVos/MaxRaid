package maxdevos.maxraid.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Spider;

public class SpiderSpeedAttackGoal extends MeleeAttackGoal {

    public SpiderSpeedAttackGoal(Spider entityspider, double speed) {
        super(entityspider, speed, true);
    }

    public boolean canUse() {
        return super.canUse() && !this.mob.isVehicle();
    }

    public boolean canContinueToUse() {
        return super.canContinueToUse();
    }

    protected double getAttackReachSqr(LivingEntity entityliving) {
        return (double)(4.0F + entityliving.getBbWidth());
    }
}