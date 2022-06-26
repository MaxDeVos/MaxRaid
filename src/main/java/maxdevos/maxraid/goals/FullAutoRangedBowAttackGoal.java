package maxdevos.maxraid.goals;
import java.util.EnumSet;

import maxdevos.maxraid.items.weapons.projecticles.BaseArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Items;

public class FullAutoRangedBowAttackGoal<T extends Monster & RangedAttackMob> extends Goal {
    private final T mob;
    private final double speedModifier;
    private int attackIntervalMin;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public FullAutoRangedBowAttackGoal(T var0, double var1, int var3, float var4) {
        this.mob = var0;
        this.speedModifier = var1;
        this.attackIntervalMin = var3;
        this.attackRadiusSqr = var4 * var4;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public void setMinAttackInterval(int var0) {
        this.attackIntervalMin = var0;
    }

    public boolean canUse() {
        return this.mob.getTarget() == null ? false : this.isHoldingBow();
    }

    protected boolean isHoldingBow() {
        return this.mob.isHolding(Items.BOW);
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone()) && this.isHoldingBow();
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.mob.stopUsingItem();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity var0 = this.mob.getTarget();
        if (var0 != null) {
            double distanceToTarget = this.mob.distanceToSqr(var0.getX(), var0.getY(), var0.getZ());
            boolean lineOfSight = this.mob.getSensing().hasLineOfSight(var0);
            boolean seeTimeGreaterThanZero = this.seeTime > 0;
            if (lineOfSight != seeTimeGreaterThanZero) {
                this.seeTime = 0;
            }

            if (lineOfSight) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (!(distanceToTarget > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                this.mob.getNavigation().stop();
                ++this.strafingTime;
            } else {
                this.mob.getNavigation().moveTo(var0, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if ((double)this.mob.getRandom().nextFloat() < 0.3) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.mob.getRandom().nextFloat() < 0.3) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (distanceToTarget > (double)(this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (distanceToTarget < (double)(this.attackRadiusSqr * 0.25F)) {
                    this.strafingBackwards = true;
                }

                this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.mob.lookAt(var0, 30.0F, 30.0F);
            } else {
                this.mob.getLookControl().setLookAt(var0, 30.0F, 30.0F);
            }

            if (this.mob.isUsingItem()) {
                if (!lineOfSight && this.seeTime < -60) {
                    this.mob.stopUsingItem();
                } else if (lineOfSight) {
                    int var5 = this.mob.getTicksUsingItem();
                    if (var5 >= 20) {
                        this.mob.stopUsingItem();
                        ((RangedAttackMob)this.mob).performRangedAttack(var0, BowItem.getPowerForTime(var5));
                        this.attackTime = this.attackIntervalMin;
                    }
                }
            } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                new BaseArrow(mob.getBukkitEntity()).shootWhereLooking();
            }

        }
    }
}

