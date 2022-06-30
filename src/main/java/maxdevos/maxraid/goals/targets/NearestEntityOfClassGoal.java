package maxdevos.maxraid.goals.targets;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class NearestEntityOfClassGoal<T extends LivingEntity> extends Goal {

    Mob mob;
    Class<T> targetType;
    int radius;
    LivingEntity target;

    public NearestEntityOfClassGoal(Mob entityinsentient, Class<T> targetType, int radius) {
        this.mob = entityinsentient;
        this.targetType = targetType;
        this.radius = radius;
    }

    @Override
    public boolean canUse() {
        this.findTarget();
        return this.target != null;
    }

    @Override
    public boolean canContinueToUse() {
        return true;
    }

    protected AABB getTargetSearchArea(double d0) {
        return this.mob.getBoundingBox().inflate(d0, d0, d0);
    }

    public void findTarget(){
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
            this.target = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType, this.getTargetSearchArea(radius), (entityliving) -> true),
                    TargetingConditions.forNonCombat().range(radius), this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.target = this.mob.level.getNearestPlayer(TargetingConditions.forCombat().range(radius), this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }
    }

    public void start(){
        this.mob.setTarget(target);
    }

}
