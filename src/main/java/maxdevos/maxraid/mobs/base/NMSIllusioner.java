package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Illusioner;
import org.bukkit.ChatColor;

public class NMSIllusioner extends Illusioner implements RaidMob {
    MaxRaid raid;

    public NMSIllusioner(MaxRaid raid) {
        super(EntityType.ILLUSIONER, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Illusioner");
    }

    public void registerRaidGoals() {
    }
}