package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Pillager;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPillager;
import org.bukkit.util.BlockVector;

public class RaidPillager extends CraftPillager implements Spawnable {

    static MaxRaid maxRaid;

    public RaidPillager(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSPillager(maxRaid));
        RaidPillager.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Pillager");
    }

    public RaidPillager(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        spawn(loc);
    }

    public void spawn(BlockVector loc){
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSPillager extends Pillager {
        MaxRaid raid;
        public NMSPillager(MaxRaid raid) {
            super(EntityType.PILLAGER, raid.getHandle().serverLevel);
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
