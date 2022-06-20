package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.EntityHitResult;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;

//TODO refactor this into BaseArrow?
public class NMSArrowBase extends Arrow {
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
