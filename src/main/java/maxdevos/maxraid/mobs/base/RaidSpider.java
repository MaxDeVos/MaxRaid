package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Spider;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftSpider;
import org.bukkit.util.BlockVector;

public class RaidSpider extends CraftSpider {

    static MaxRaid maxRaid;
    public RaidSpider(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSSpider(maxRaid));
        RaidSpider.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Spider");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSSpider extends Spider {
        public NMSSpider(MaxRaid raid) {
            super(EntityType.SPIDER, raid.getHandle().serverLevel);
        }
    }

}
