package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Evoker;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEvoker;
import org.bukkit.util.BlockVector;

public class RaidEvoker extends CraftEvoker {

    static MaxRaid maxRaid;
    public RaidEvoker(MaxRaid raid, BlockVector loc) {
        super(raid.getHandle().getLevel().getCraftServer(), new NMSEvoker(raid));
        RaidEvoker.maxRaid = raid;
        setCustomName(ChatColor.DARK_RED + "RAID Evoker");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        raid.getHandle().addMob(this.getHandle());
    }

    private static class NMSEvoker extends Evoker {
        MaxRaid raid;
        public NMSEvoker(MaxRaid raid) {
            super(EntityType.EVOKER, raid.getHandle().serverLevel);
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
