package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Phantom;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPhantom;
import org.bukkit.util.BlockVector;

public class RaidPhantom extends CraftPhantom {

    static MaxRaid maxRaid;
    public RaidPhantom(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSPhantom(maxRaid));
        RaidPhantom.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Phantom");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSPhantom extends Phantom {
        public NMSPhantom(MaxRaid raid) {
            super(EntityType.PHANTOM, raid.getHandle().serverLevel);
        }
    }

}
