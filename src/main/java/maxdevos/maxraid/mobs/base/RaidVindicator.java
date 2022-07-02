package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.items.weapons.swords.Level2Sword;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftVindicator;
import org.bukkit.util.BlockVector;

public class RaidVindicator extends CraftVindicator implements Spawnable {

    static MaxRaid maxRaid;

    public RaidVindicator(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSVindicator(maxRaid));
        RaidVindicator.maxRaid = maxRaid;
        setPersistent(true);
        getHandle().getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("raid bonus", 30f, AttributeModifier.Operation.ADDITION));
        getHandle().setHealth(30f);
        setCustomName(ChatColor.DARK_RED + "RAID Vindicator");
        getEquipment().setItemInMainHand(new Level2Sword());
    }

    public RaidVindicator(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSVindicator extends Vindicator {
        MaxRaid raid;

        public NMSVindicator(MaxRaid raid) {
            super(EntityType.VINDICATOR, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
        }

//        @Override
//        protected void registerGoals(){
//            goalSelector.removeAllGoals();
//            targetSelector.removeAllGoals();
//        }

        protected void registerRaidGoals() {
        }
    }

}
