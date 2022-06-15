package maxdevos.maxraid.mobs.nmsMobs;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Spider;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftSpider;
import org.bukkit.util.BlockVector;

public class RaidSpider extends CraftSpider {

    static MaxRaid raid;
    public RaidSpider(MaxRaid raid, BlockVector loc) {
        super(raid.getHandle().getLevel().getCraftServer(), new NMSSpider(raid));
        RaidSpider.raid = raid;
        setCustomName(ChatColor.DARK_RED + "RAID Spider");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        this.getHandle().goalSelector.removeAllGoals();
        this.getHandle().getLevel().addFreshEntity(this.getHandle());
        raid.getHandle().addMob(this.getHandle());
    }

    private static class NMSSpider extends Spider {
        public NMSSpider(MaxRaid raid) {
            super(EntityType.SPIDER, raid.getHandle().serverLevel);
        }

        @Override
        public boolean hurt(DamageSource damagesource, float f) {
            raid.getHandle().updateBossbar();
            return super.hurt(damagesource, f);
        }

        @Override
        public void die(DamageSource damagesource) {
            raid.getHandle().updateBossbar();
            super.die(damagesource);
        }
    }

}
