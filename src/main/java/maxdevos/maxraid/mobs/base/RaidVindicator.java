package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Vindicator;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftVindicator;
import org.bukkit.util.BlockVector;

public class RaidVindicator extends CraftVindicator implements Spawnable {

    static MaxRaid maxRaid;

    public RaidVindicator(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSVindicator(maxRaid));
        RaidVindicator.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Vindicator");
    }

    public RaidVindicator(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
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
