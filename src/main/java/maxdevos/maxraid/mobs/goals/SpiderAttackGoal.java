package maxdevos.maxraid.mobs.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Spider;

public class SpiderAttackGoal extends MeleeAttackGoal {
    public SpiderAttackGoal(Spider entityspider) {
        super(entityspider, 3.0, true);
    }

    public boolean canUse() {
        return true;
    }

    public boolean canContinueToUse() {
        return true;
    }

    protected double getAttackReachSqr(LivingEntity entityliving) {
        return (double)(4.0F + entityliving.getBbWidth());
    }
}