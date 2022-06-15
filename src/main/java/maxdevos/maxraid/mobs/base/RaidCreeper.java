package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftCreeper;
import org.bukkit.util.BlockVector;

public class RaidCreeper extends CraftCreeper {

    static MaxRaid maxRaid;
    public RaidCreeper(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSCreeper(maxRaid));
        RaidCreeper.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID CREEPER");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSCreeper extends Creeper {
        public NMSCreeper(MaxRaid raid) {
            super(EntityType.CREEPER, raid.getHandle().serverLevel);
        }
    }

}
