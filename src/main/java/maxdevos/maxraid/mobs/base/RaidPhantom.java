package maxdevos.maxraid.mobs.base;

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

public class RaidPhantom extends CraftPhantom implements Spawnable {

    static MaxRaid maxRaid;

    public RaidPhantom(MaxRaid maxRaid, NMSPhantom instance) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), instance);
        RaidPhantom.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Phantom");
    }

    public RaidPhantom(MaxRaid maxRaid, BlockVector loc, BlockVector target) {
        this(maxRaid, new NMSPhantom(maxRaid));
        this.getHandle().getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1f);
        spawn(loc);
    }

    public RaidPhantom(MaxRaid maxRaid, BlockVector loc, BlockVector target, NMSPhantom instance) {
        this(maxRaid, instance);
        this.getHandle().getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1f);
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    public static class NMSPhantom extends Phantom {
        public MaxRaid raid;

        public NMSPhantom(MaxRaid raid) {
            super(EntityType.PHANTOM, raid.getHandle().serverLevel);
            setPhantomSize(5);
            this.raid = raid;
            registerRaidGoals();
            this.moveControl = new PhantomMoveControl(this);
        }

        @Override
        protected void registerGoals() {
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        public void handleCollision(boolean horizontal){
            setYRot(NMSPhantom.this.getYRot() + 180.0F);
        }

        protected void registerRaidGoals() {
            goalSelector.addGoal(1, new PhantomMoveToPoint(this));
//            goalSelector.addGoal(2, new ZombieAttackGoal(this, 2.0, true));
//            goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));
//            goalSelector.addGoal(4, new LookAtPointGoal(this, raid.getVillageCenter()));

//            targetSelector.addGoal(1, new HurtByTargetGoal(this));
//            targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
//            targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        }

        /**
         * Make immune from sunburn
         */
        @Override
        protected boolean isSunBurnTick() {
            return false;
        }

        private class PhantomMoveControl extends MoveControl {

            public PhantomMoveControl(Mob entityinsentient) {
                super(entityinsentient);
            }

            public void tick() {
                if (NMSPhantom.this.horizontalCollision) {
                    handleCollision(true);
                }
                if (NMSPhantom.this.verticalCollision ||
                        NMSPhantom.this.verticalCollisionBelow){
                    handleCollision(false);
                }

                double d0 = this.getWantedX() - NMSPhantom.this.getX();
                double d1 = this.getWantedY() - NMSPhantom.this.getY();
                double d2 = this.getWantedZ() - NMSPhantom.this.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                if (Math.abs(d3) > 9.999999747378752E-6) {
                    double d4 = 1.0 - Math.abs(d1 * 0.699999988079071) / d3;
                    d0 *= d4;
                    d2 *= d4;
                    d3 = Math.sqrt(d0 * d0 + d2 * d2);
                    double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                    float f = NMSPhantom.this.getYRot();
                    float f1 = (float) Mth.atan2(d2, d0);
                    float f2 = Mth.wrapDegrees(NMSPhantom.this.getYRot() + 90.0F);
                    float f3 = Mth.wrapDegrees(f1 * 57.295776F);
                    NMSPhantom.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                    NMSPhantom.this.yBodyRot = NMSPhantom.this.getYRot();
                    if (Mth.degreesDifferenceAbs(f, NMSPhantom.this.getYRot()) < 3.0F) {
                        this.speedModifier = Mth.approach((float) this.getSpeedModifier(), 1.8F, 0.005F * (1.8F / (float) this.getSpeedModifier()));
                    } else {
                        this.speedModifier = Mth.approach((float) this.getSpeedModifier(), 0.2F, 0.025F);
                    }

                    float f4 = (float) (-(Mth.atan2(-d1, d3) * 57.2957763671875));
                    NMSPhantom.this.setXRot(f4);
                    float f5 = NMSPhantom.this.getYRot() + 90.0F;
                    double d6 = (this.speedModifier * Mth.cos(f5 * 0.017453292F)) * Math.abs(d0 / d5);
                    double d7 = (this.speedModifier * Mth.sin(f5 * 0.017453292F)) * Math.abs(d2 / d5);
                    double d8 = (this.speedModifier * Mth.sin(f4 * 0.017453292F)) * Math.abs(d1 / d5);
                    Vec3 vec3d = NMSPhantom.this.getDeltaMovement();
                    NMSPhantom.this.setDeltaMovement(vec3d.add((new Vec3(d6, d8, d7)).subtract(vec3d).scale(0.2)));
                }

            }
        }

    }

}
