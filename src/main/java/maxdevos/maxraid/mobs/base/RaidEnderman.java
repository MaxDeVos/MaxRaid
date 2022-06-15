package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEnderman;
import org.bukkit.util.BlockVector;

public class RaidEnderman extends CraftEnderman {

    static MaxRaid maxRaid;
    public RaidEnderman(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSEnderman(maxRaid));
        RaidEnderman.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Enderman");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSEnderman extends EnderMan {
        public NMSEnderman(MaxRaid raid) {
            super(EntityType.ENDERMAN, raid.getHandle().serverLevel);
        }
    }

}
