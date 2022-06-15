package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.WitherSkeleton;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftWitherSkeleton;
import org.bukkit.util.BlockVector;

public class RaidWitherSkeleton extends CraftWitherSkeleton {

    static MaxRaid maxRaid;
    public RaidWitherSkeleton(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSWitherSkeleton(maxRaid));
        RaidWitherSkeleton.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID WitherSkeleton");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSWitherSkeleton extends WitherSkeleton {
        public NMSWitherSkeleton(MaxRaid raid) {
            super(EntityType.WITHER_SKELETON, raid.getHandle().serverLevel);
        }
    }

}
