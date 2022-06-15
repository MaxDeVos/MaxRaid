package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ghast;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftGhast;
import org.bukkit.util.BlockVector;

public class RaidGhast extends CraftGhast {

    static MaxRaid maxRaid;
    public RaidGhast(MaxRaid raid, BlockVector loc) {
        super(raid.getHandle().getLevel().getCraftServer(), new NMSGhast(raid));
        RaidGhast.maxRaid = raid;
        setCustomName(ChatColor.DARK_RED + "RAID Ghast");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        raid.getHandle().addMob(this.getHandle());
    }

    private static class NMSGhast extends Ghast {
        public NMSGhast(MaxRaid raid) {
            super(EntityType.GHAST, raid.getHandle().serverLevel);
        }
    }

}
