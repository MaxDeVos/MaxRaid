package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftZombie;
import org.bukkit.util.BlockVector;

public class RaidZombie extends CraftZombie {

    static MaxRaid maxRaid;
    public RaidZombie(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSZombie(maxRaid));
        RaidZombie.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Zombie");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSZombie extends Zombie {
        public NMSZombie(MaxRaid raid) {
            super(EntityType.ZOMBIE, raid.getHandle().serverLevel);
        }
    }

}
