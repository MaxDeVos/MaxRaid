package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Vindicator;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftVindicator;
import org.bukkit.util.BlockVector;

public class RaidVindicator extends CraftVindicator {

    static MaxRaid maxRaid;
    public RaidVindicator(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSVindicator(maxRaid));
        RaidVindicator.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Vindicator");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSVindicator extends Vindicator {
        public NMSVindicator(MaxRaid raid) {
            super(EntityType.VINDICATOR, raid.getHandle().serverLevel);
        }
    }

}
