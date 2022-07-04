package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Witch;
import org.bukkit.ChatColor;

public class NMSWitch extends Witch implements RaidMob {
    MaxRaid raid;

    public NMSWitch(MaxRaid raid) {
        super(EntityType.WITCH, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Witch");
    }

    public void registerRaidGoals() {

    }
}