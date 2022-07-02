package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.PhantomDropBombs;
import maxdevos.maxraid.goals.PhantomMoveToPoint;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPhantom;
import org.bukkit.util.BlockVector;

public class BomberPhantom extends CraftPhantom {

    public static MaxRaid maxRaid;
    private final BlockVector loc;

    public BomberPhantom(MaxRaid maxRaid, BlockVector loc, BlockVector target, double bombsPerSecond) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSBomberPhantom(maxRaid, bombsPerSecond));
        BomberPhantom.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "Bomber Phantom");
        this.loc = loc;
        this.getHandle().getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 0.25f);
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle(), false);
    }

    private static class NMSBomberPhantom extends Phantom {
        MaxRaid raid;
        double bombsPerSecond;

        public NMSBomberPhantom(MaxRaid raid, double bombsPerSecond) {
            super(EntityType.PHANTOM, raid.getHandle().serverLevel);
            setPhantomSize(5);
            this.bombsPerSecond = bombsPerSecond;
            this.raid = raid;
            registerRaidGoals();
            this.moveControl = new PhantomMoveControl(this, 0.01f);
        }

        @Override
        protected void registerGoals() {
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {
            goalSelector.addGoal(1, new PhantomDropBombs(this, bombsPerSecond));
            goalSelector.addGoal(2, new PhantomMoveToPoint(this, 0.01f));
        }

        /**
         * Make immune from sunburn
         */
        @Override
        protected boolean isSunBurnTick() {
            return false;
        }

        private class PhantomMoveControl extends MoveControl {

            public PhantomMoveControl(Mob entityinsentient, float speed) {
                super(entityinsentient);
                this.speedModifier = speed;
            }

            public void tick() {
                if (NMSBomberPhantom.this.horizontalCollision) {
                    NMSBomberPhantom.this.setYRot(NMSBomberPhantom.this.getYRot() + 180.0F);
                }

                double d0 = this.getWantedX() - NMSBomberPhantom.this.getX();
                double d1 = this.getWantedY() - NMSBomberPhantom.this.getY();
                double d2 = this.getWantedZ() - NMSBomberPhantom.this.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                if (Math.abs(d3) > 9.999999747378752E-6) {
                    double d4 = 1.0 - Math.abs(d1 * 0.699999988079071) / d3;
                    d0 *= d4;
                    d2 *= d4;
                    d3 = Math.sqrt(d0 * d0 + d2 * d2);
                    double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                    float f = NMSBomberPhantom.this.getYRot();
                    float f1 = (float) Mth.atan2(d2, d0);
                    float f2 = Mth.wrapDegrees(NMSBomberPhantom.this.getYRot() + 90.0F);
                    float f3 = Mth.wrapDegrees(f1 * 57.295776F);
                    NMSBomberPhantom.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                    NMSBomberPhantom.this.yBodyRot = NMSBomberPhantom.this.getYRot();
                    if (Mth.degreesDifferenceAbs(f, NMSBomberPhantom.this.getYRot()) < 3.0F) {
                        this.speedModifier = Mth.approach((float) this.getSpeedModifier(), 1.8F, 0.005F * (1.8F / (float) this.getSpeedModifier()));
                    } else {
                        this.speedModifier = Mth.approach((float) this.getSpeedModifier(), 0.2F, 0.025F);
                    }

                    float f4 = (float) (-(Mth.atan2(-d1, d3) * 57.2957763671875));
                    NMSBomberPhantom.this.setXRot(f4);
                    float f5 = NMSBomberPhantom.this.getYRot() + 90.0F;
                    double d6 = (this.speedModifier * Mth.cos(f5 * 0.017453292F)) * Math.abs(d0 / d5);
                    double d7 = (this.speedModifier * Mth.sin(f5 * 0.017453292F)) * Math.abs(d2 / d5);
                    double d8 = (this.speedModifier * Mth.sin(f4 * 0.017453292F)) * Math.abs(d1 / d5);
                    Vec3 vec3d = NMSBomberPhantom.this.getDeltaMovement();
                    NMSBomberPhantom.this.setDeltaMovement(vec3d.add((new Vec3(d6, d8, d7)).subtract(vec3d).scale(0.1)));
                }

            }
        }

    }

}
