package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Illusioner;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftIllusioner;
import org.bukkit.util.BlockVector;

public class RaidIllusioner extends CraftIllusioner {

    static MaxRaid maxRaid;
    public RaidIllusioner(MaxRaid raid, BlockVector loc) {
        super(raid.getHandle().getLevel().getCraftServer(), new NMSIllusioner(raid));
        RaidIllusioner.maxRaid = raid;
        setCustomName(ChatColor.DARK_RED + "RAID Illusioner");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        raid.getHandle().addMob(this.getHandle());
    }

    private static class NMSIllusioner extends Illusioner {
        public NMSIllusioner(MaxRaid raid) {
            super(EntityType.ILLUSIONER, raid.getHandle().serverLevel);
        }
    }

}