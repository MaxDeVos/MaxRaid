package maxdevos.maxraid.mobs.nms;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.mobs.temp.RaidPhantom;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;
import org.bukkit.ChatColor;

public class NMSPhantom extends Phantom implements RaidMob {
    MaxRaid raid;

    public NMSPhantom(MaxRaid raid) {
        super(EntityType.PHANTOM, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Phantom");
    }

    public void registerRaidGoals() {
    }

    public void handleCollision(boolean horizontal){
        setYRot(getYRot() + 180.0F);
    }

    protected boolean isSunBurnTick() {
        return false;
    }

    private class PhantomMoveControl extends MoveControl {

        public PhantomMoveControl(Mob entityinsentient) {
            super(entityinsentient);
        }

        public void tick() {
            if (horizontalCollision) {
                handleCollision(true);
            }
            if (verticalCollision ||
                    verticalCollisionBelow){
                handleCollision(false);
            }

            double d0 = this.getWantedX() - getX();
            double d1 = this.getWantedY() - getY();
            double d2 = this.getWantedZ() - getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            if (Math.abs(d3) > 9.999999747378752E-6) {
                double d4 = 1.0 - Math.abs(d1 * 0.699999988079071) / d3;
                d0 *= d4;
                d2 *= d4;
                d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                float f = getYRot();
                float f1 = (float) Mth.atan2(d2, d0);
                float f2 = Mth.wrapDegrees(getYRot() + 90.0F);
                float f3 = Mth.wrapDegrees(f1 * 57.295776F);
                setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                yBodyRot = getYRot();
                if (Mth.degreesDifferenceAbs(f, getYRot()) < 3.0F) {
                    this.speedModifier = Mth.approach((float) this.getSpeedModifier(), 1.8F, 0.005F * (1.8F / (float) this.getSpeedModifier()));
                } else {
                    this.speedModifier = Mth.approach((float) this.getSpeedModifier(), 0.2F, 0.025F);
                }

                float f4 = (float) (-(Mth.atan2(-d1, d3) * 57.2957763671875));
                setXRot(f4);
                float f5 = getYRot() + 90.0F;
                double d6 = (this.speedModifier * Mth.cos(f5 * 0.017453292F)) * Math.abs(d0 / d5);
                double d7 = (this.speedModifier * Mth.sin(f5 * 0.017453292F)) * Math.abs(d2 / d5);
                double d8 = (this.speedModifier * Mth.sin(f4 * 0.017453292F)) * Math.abs(d1 / d5);
                Vec3 vec3d = getDeltaMovement();
                setDeltaMovement(vec3d.add((new Vec3(d6, d8, d7)).subtract(vec3d).scale(0.2)));
            }

        }
    }

}