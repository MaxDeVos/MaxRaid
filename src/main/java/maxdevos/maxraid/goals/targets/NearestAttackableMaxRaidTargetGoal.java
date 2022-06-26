package maxdevos.maxraid.goals.targets;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;



public class NearestAttackableMaxRaidTargetGoal<T extends LivingEntity> extends TargetGoal {

    private static final int DEFAULT_RANDOM_INTERVAL = 10;
    protected final Class<T> targetType;
    protected final int randomInterval;
    @Nullable
    protected LivingEntity target;
    protected TargetingConditions targetConditions;

    public NearestAttackableMaxRaidTargetGoal(Mob entityinsentient, Class<T> oclass) {
        this(entityinsentient, oclass, 10, false, false, null);
    }

    public NearestAttackableMaxRaidTargetGoal(Mob entityinsentient, Class<T> oclass, boolean mustSee) {
        this(entityinsentient, oclass, 10, mustSee, false, null);
    }

    public NearestAttackableMaxRaidTargetGoal(Mob entityinsentient, Class<T> oclass, int i, boolean mustSee, boolean mustReach, @Nullable Predicate<LivingEntity> predicate) {
        super(entityinsentient, mustSee, mustReach);
        this.targetType = oclass;
        this.randomInterval = reducedTickDelay(i);
        this.setFlags(EnumSet.of(Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(50).ignoreLineOfSight().selector(predicate);
    }

    public NearestAttackableMaxRaidTargetGoal(Mob entityinsentient, Class<T> oclass, int dist) {
        super(entityinsentient, true, false);
        this.targetType = oclass;
        this.randomInterval = reducedTickDelay(10);
        this.setFlags(EnumSet.of(Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(dist).ignoreLineOfSight().selector(null);
    }

    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
            return false;
        } else {
            this.findTarget();
            return this.target != null;
        }
    }

    protected AABB getTargetSearchArea(double d0) {
        return this.mob.getBoundingBox().inflate(d0, 4.0, d0);
    }

    protected void findTarget() {
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
            this.target = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (entityliving) -> {
                return true;
            }), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.target = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

    }

    public void start() {
        this.mob.setTarget(this.target, this.target instanceof ServerPlayer ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.CLOSEST_ENTITY, true);
        super.start();
    }

    public void setTarget(@Nullable LivingEntity entityliving) {
        this.target = entityliving;
    }
}
