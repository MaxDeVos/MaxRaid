package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Spider;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftSpider;
import org.bukkit.util.BlockVector;

/** Limbo Spider can pass through single-block openings */
//TODO limbo spider
public class LimboSpider extends CraftSpider {

    static MaxRaid maxRaid;
    public LimboSpider(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSSpider(maxRaid));
        LimboSpider.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "Limbo Spider");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSSpider extends Spider {
        public NMSSpider(MaxRaid raid) {
            super(EntityType.SPIDER, raid.getHandle().serverLevel);
        }
    }

}
