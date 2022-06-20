package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;

public class BaseArrow extends CraftArrow {

    protected CraftEntity archer;

    public float velocity = 1f;
    public float spread = 12f;

    // To modify more advanced behavior, copy this class and create a private static class mob-style.
    public BaseArrow(CraftEntity archer) {
        this(archer, new NMSArrowBase(archer));
    }

    public BaseArrow(CraftEntity archer, AbstractArrow customEntity) {
        super(archer.getHandle().getLevel().getCraftServer(), customEntity);
        this.archer = archer;
        configureArrow();
        shootWhereLooking();
    }

    /** Override this to modify arrow */
    public void configureArrow(){}

    public void shootWhereLooking(){
        Vec3 eyePos = archer.getHandle().getEyePosition();
        Vec3 lookAng = archer.getHandle().getLookAngle();

        getHandle().moveTo(eyePos.x, eyePos.y, eyePos.z,
                archer.getLocation().getYaw(), archer.getLocation().getPitch());
        getHandle().shoot(lookAng.x, lookAng.y, lookAng.z, velocity, spread);
        archer.getHandle().getLevel().addFreshEntity(getHandle());
    }
}
