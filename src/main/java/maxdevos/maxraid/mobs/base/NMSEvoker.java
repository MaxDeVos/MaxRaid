package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Evoker;
import org.bukkit.ChatColor;

public class NMSEvoker extends Evoker implements RaidMob {
    MaxRaid raid;

    public NMSEvoker(MaxRaid raid) {
        super(EntityType.EVOKER, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Evoker");
    }

    public void registerRaidGoals() {

    }
}