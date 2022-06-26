package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public class BaseArrow extends CraftArrow {

    protected CraftEntity archer;

    public float velocity = 1.5f;
    public float spread = 12f;

    public BaseArrow(CraftEntity archer) {
        this(archer, new NMSArrowBase(archer));
    }

    public BaseArrow(CraftEntity archer, AbstractArrow customEntity) {
        super(archer.getHandle().getLevel().getCraftServer(), customEntity);
        this.archer = archer;
    }

    public void shootWhereLooking(){
        shootWhereLooking(this.velocity, this.spread);
    }

    public void shootWhereLooking(float velocity, float spread){
        Vec3 eyePos = archer.getHandle().getEyePosition();
        Vec3 lookAng = archer.getHandle().getLookAngle();

        System.out.println("EYEPOS: " + eyePos + "    LOOKANG: " + lookAng);

        getHandle().projectileSource = (ProjectileSource) archer;
        getHandle().moveTo(eyePos.x, eyePos.y, eyePos.z,
                archer.getLocation().getYaw(), archer.getLocation().getPitch());
        getHandle().shoot(lookAng.x, lookAng.y, lookAng.z, velocity, spread);
        archer.getHandle().getLevel().addFreshEntity(getHandle());
    }

    public void shootAtTarget(CraftEntity target) {

    }

    public void shootAtPoint(Vec3 point) {
        Vec3 eyePos = archer.getHandle().getEyePosition();
        Vec3 direction = point.subtract(archer.getHandle().getEyePosition());

        System.out.println("EYEPOS: " + eyePos + "    DIRECTION: " + direction);

        getHandle().projectileSource = (ProjectileSource) archer;
        getHandle().moveTo(eyePos.x, eyePos.y, eyePos.z,
                -archer.getLocation().getYaw(), -archer.getLocation().getPitch());
        getHandle().shoot(direction.x, direction.y, direction.z, velocity, spread);
        archer.getHandle().getLevel().addFreshEntity(getHandle());
    }

    public static class NMSArrowBase extends Arrow {
        CraftEntity archer;
        public NMSArrowBase(CraftEntity archer) {
            super(EntityType.ARROW, archer.getHandle().getLevel());
            this.archer = archer;
        }

        @Override
        protected void onHitEntity(EntityHitResult movingobjectpositionentity) {
            if(!movingobjectpositionentity.getEntity().getUUID().equals(archer.getHandle().getUUID())){
                super.onHitEntity(movingobjectpositionentity);
                this.life = 100;
            }
        }
    }
}
