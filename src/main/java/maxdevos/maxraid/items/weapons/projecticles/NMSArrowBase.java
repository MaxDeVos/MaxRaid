package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Arrow;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;

public class NMSArrowBase extends Arrow {

    public NMSArrowBase(CraftEntity archer) {
        super(EntityType.ARROW, archer.getHandle().getLevel());
    }

}
