package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Pillager;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPillager;
import org.bukkit.util.BlockVector;

public class RaidPillager extends CraftPillager {

    static MaxRaid maxRaid;
    public RaidPillager(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSPillager(maxRaid));
        RaidPillager.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Pillager");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSPillager extends Pillager {
        public NMSPillager(MaxRaid raid) {
            super(EntityType.PILLAGER, raid.getHandle().serverLevel);
        }
    }

}
