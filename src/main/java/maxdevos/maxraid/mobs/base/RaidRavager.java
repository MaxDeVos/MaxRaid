package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ravager;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftRavager;
import org.bukkit.util.BlockVector;

public class RaidRavager extends CraftRavager {

    static MaxRaid maxRaid;
    public RaidRavager(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSRavager(maxRaid));
        RaidRavager.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Ravager");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSRavager extends Ravager {
        MaxRaid raid;
        public NMSRavager(MaxRaid raid) {
            super(EntityType.RAVAGER, raid.getHandle().serverLevel);
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
