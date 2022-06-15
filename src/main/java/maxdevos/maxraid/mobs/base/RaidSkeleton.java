package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftSkeleton;
import org.bukkit.util.BlockVector;

public class RaidSkeleton extends CraftSkeleton {

    static MaxRaid maxRaid;
    public RaidSkeleton(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSSkeleton(maxRaid));
        RaidSkeleton.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Skeleton");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSSkeleton extends Skeleton {
        public NMSSkeleton(MaxRaid raid) {
            super(EntityType.SKELETON, raid.getHandle().serverLevel);
        }
    }

}
