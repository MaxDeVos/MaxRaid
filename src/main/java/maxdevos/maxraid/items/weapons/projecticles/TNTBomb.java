package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftMob;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftTNTPrimed;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public class TNTBomb extends CraftTNTPrimed {


    public TNTBomb(CraftMob bomber, Location l) {
        super(bomber.getHandle().getLevel().getCraftServer(), new NMSTNTBomb(bomber.getHandle().getLevel()));
        this.getHandle().setPos(l.getX(), l.getY(), l.getZ());
        this.setVelocity(new Vector(0, -2, 0));
        this.setFuseTicks(40);
    }

    private static class NMSTNTBomb extends PrimedTnt {

        public NMSTNTBomb(Level world) {
            super(EntityType.TNT, world);
        }
    }
}
