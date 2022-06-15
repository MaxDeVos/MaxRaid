package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Blaze;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftBlaze;
import org.bukkit.util.BlockVector;

public class RaidBlaze extends CraftBlaze {

    static MaxRaid maxRaid;
    public RaidBlaze(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSBlaze(maxRaid));
        RaidBlaze.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Blaze");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSBlaze extends Blaze {
        public NMSBlaze(MaxRaid raid) {
            super(EntityType.BLAZE, raid.getHandle().serverLevel);
        }
    }

}
