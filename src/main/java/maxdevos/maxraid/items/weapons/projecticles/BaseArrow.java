package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;
import org.bukkit.projectiles.ProjectileSource;
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
        Vector archerVelocity = archer.getVelocity();

        getHandle().projectileSource = (ProjectileSource) archer;
        getHandle().moveTo(eyePos.x, eyePos.y, eyePos.z,
                archer.getLocation().getYaw(), archer.getLocation().getPitch());
        getHandle().shoot(lookAng.x, lookAng.y, lookAng.z, velocity, spread);
        archer.getHandle().getLevel().addFreshEntity(getHandle());
    }
}
