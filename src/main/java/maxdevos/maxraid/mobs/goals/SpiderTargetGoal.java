package maxdevos.maxraid.mobs.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Spider;

public class SpiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    public SpiderTargetGoal(Spider entityspider, Class<T> oclass) {
        super(entityspider, oclass, false);
    }

    public boolean canUse() {
        return true;
    }
}