package maxdevos.maxraid.mobs.nms;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Pillager;
import org.bukkit.ChatColor;

public class NMSPillager extends Pillager implements RaidMob {
    MaxRaid raid;

    public NMSPillager(MaxRaid raid) {
        super(EntityType.PILLAGER, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Pillager");
    }

    public void registerRaidGoals() {

    }
}