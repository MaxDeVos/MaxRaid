package maxdevos.maxraid.mobs.nms;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Ravager;
import org.bukkit.ChatColor;

public class NMSRavager extends Ravager implements RaidMob {
    MaxRaid raid;

    public NMSRavager(MaxRaid raid) {
        super(EntityType.RAVAGER, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Ravager");
    }

    public void registerRaidGoals() {

    }
}