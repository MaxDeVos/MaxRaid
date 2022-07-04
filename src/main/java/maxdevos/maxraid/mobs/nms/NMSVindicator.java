package maxdevos.maxraid.mobs.nms;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Vindicator;
import org.bukkit.ChatColor;

public class NMSVindicator extends Vindicator implements RaidMob {
    MaxRaid raid;

    public NMSVindicator(MaxRaid raid) {
        super(EntityType.VINDICATOR, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Vindicator");
    }

    public void registerRaidGoals() {

    }
}