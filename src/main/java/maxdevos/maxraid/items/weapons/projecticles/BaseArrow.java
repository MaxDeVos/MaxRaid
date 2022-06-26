package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public class BaseArrow extends CraftArrow {

    protected CraftEntity archer;

    public float velocity = 1.5f;
    public float spread = 12f;

    boolean shootTracer = false;

    public BaseArrow(CraftEntity archer) {
        this(archer, new NMSArrowBase(archer), false);
    }

    public BaseArrow(CraftEntity archer, AbstractArrow customEntity) {
        this(archer, customEntity, false);
    }

    public BaseArrow(CraftEntity archer, AbstractArrow customEntity, boolean shootTracer){
        super(archer.getHandle().getLevel().getCraftServer(), customEntity);
        this.archer = archer;
        this.shootTracer = shootTracer;
    }

    public void shootWhereLooking(){
        shootWhereLooking(this.velocity, this.spread);
    }

    public void shootWhereLooking(float velocity, float spread){
        Vec3 eyePos = archer.getHandle().getEyePosition();
        Vec3 lookAng = archer.getHandle().getLookAngle();

        getHandle().projectileSource = (ProjectileSource) archer;
        getHandle().moveTo(eyePos.x, eyePos.y, eyePos.z,
                archer.getLocation().getYaw(), archer.getLocation().getPitch());
        getHandle().shoot(lookAng.x, lookAng.y, lookAng.z, velocity, spread);
        archer.getHandle().getLevel().addFreshEntity(getHandle());

        if(shootTracer){
            shootTracer();
        }

    }

    public void shootAtTarget(CraftEntity target) {

    }

    public void shootAtPoint(Vec3 point) {
        Vec3 eyePos = archer.getHandle().getEyePosition();
        Vec3 direction = point.subtract(archer.getHandle().getEyePosition());

        getHandle().projectileSource = (ProjectileSource) archer;
        getHandle().moveTo(eyePos.x, eyePos.y, eyePos.z,
                -archer.getLocation().getYaw(), -archer.getLocation().getPitch());
        getHandle().shoot(direction.x, direction.y, direction.z, velocity, spread);
        archer.getHandle().getLevel().addFreshEntity(getHandle());

        if(shootTracer){
            shootTracer();
        }

    }

    public void shootTracer(){
        for(int i = 0; i < 1000; i++){
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(138, 138, 138), 1F);
            archer.getWorld().spawnParticle(Particle.REDSTONE, archer.getHandle().getEyePosition().x + archer.getHandle().getViewVector(1.0f).x * (0.25) * i,
                    archer.getHandle().getEyePosition().y + archer.getHandle().getViewVector(1.0f).y * (0.25) * i,
                    archer.getHandle().getEyePosition().z + archer.getHandle().getViewVector(1.0f).z * (0.25) * i
                    , 1, dustOptions);
        }
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
