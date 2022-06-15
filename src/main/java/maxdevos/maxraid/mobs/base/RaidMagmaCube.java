package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.MagmaCube;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftMagmaCube;
import org.bukkit.util.BlockVector;

public class RaidMagmaCube extends CraftMagmaCube {

    static MaxRaid maxRaid;
    public RaidMagmaCube(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSMagmaCube(maxRaid));
        RaidMagmaCube.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID MagmaCube");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSMagmaCube extends MagmaCube {
        public NMSMagmaCube(MaxRaid raid) {
            super(EntityType.MAGMA_CUBE, raid.getHandle().serverLevel);
        }
    }

}
