package maxdevos.maxraid.goals;

import maxdevos.maxraid.mobs.temp.RaidBlaze;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;

import java.util.EnumSet;

public class MaxRaidBlazeAttackGoal extends Goal {
    private final RaidBlaze.NMSBlaze blaze;
    private int attackStep;
    private int attackTime;
    private int lastSeen;

    public MaxRaidBlazeAttackGoal(RaidBlaze.NMSBlaze var0) {
        this.blaze = var0;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity var0 = this.blaze.getTarget();
        return var0 != null && var0.isAlive() && this.blaze.canAttack(var0);
    }

    public void start() {
        this.attackStep = 0;
    }

    public void stop() {
        this.blaze.setCharged(false);
        this.lastSeen = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        --this.attackTime;
        LivingEntity var0 = this.blaze.getTarget();
        if (var0 != null) {
            boolean var1 = this.blaze.getSensing().hasLineOfSight(var0);
            if (var1) {
                this.lastSeen = 0;
            } else {
                ++this.lastSeen;
            }

            double var2 = this.blaze.distanceToSqr(var0);
            if (var2 < 4.0) {
                if (!var1) {
                    return;
                }

                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.blaze.doHurtTarget(var0);
                }

                this.blaze.getMoveControl().setWantedPosition(var0.getX(), var0.getY(), var0.getZ(), 1.0);
            } else if (var2 < this.getFollowDistance() * this.getFollowDistance() && var1) {
                double var4 = var0.getX() - this.blaze.getX();
                double var6 = var0.getY(0.5) - this.blaze.getY(0.5);
                double var8 = var0.getZ() - this.blaze.getZ();
                if (this.attackTime <= 0) {
                    ++this.attackStep;
                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                        this.blaze.setCharged(true);
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                        this.blaze.setCharged(false);
                    }

                    if (this.attackStep > 1) {
                        double var10 = Math.sqrt(Math.sqrt(var2)) * 0.5;
                        if (!this.blaze.isSilent()) {
                            this.blaze.level.levelEvent((Player)null, 1018, this.blaze.blockPosition(), 0);
                        }

                        for(int var12 = 0; var12 < 1; ++var12) {
                            SmallFireball var13 = new SmallFireball(this.blaze.level, this.blaze, var4 + this.blaze.getRandom().nextGaussian() * var10, var6, var8 + this.blaze.getRandom().nextGaussian() * var10);
                            var13.setPos(var13.getX(), this.blaze.getY(0.5) + 0.5, var13.getZ());
                            this.blaze.level.addFreshEntity(var13);
                        }
                    }
                }

                this.blaze.getLookControl().setLookAt(var0, 10.0F, 10.0F);
            } else if (this.lastSeen < 5) {
                this.blaze.getMoveControl().setWantedPosition(var0.getX(), var0.getY(), var0.getZ(), 1.0);
            }

            super.tick();
        }
    }

    private double getFollowDistance() {
        return this.blaze.getAttributeValue(Attributes.FOLLOW_RANGE);
    }
}
