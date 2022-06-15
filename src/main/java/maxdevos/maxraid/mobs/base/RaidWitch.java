package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Witch;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftWitch;
import org.bukkit.util.BlockVector;

public class RaidWitch extends CraftWitch {

    static MaxRaid maxRaid;
    public RaidWitch(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSWitch(maxRaid));
        RaidWitch.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Witch");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSWitch extends Witch {
        public NMSWitch(MaxRaid raid) {
            super(EntityType.WITCH, raid.getHandle().serverLevel);
        }
    }

}
