package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEvoker;
import org.bukkit.util.BlockVector;

public class RaidEvoker extends CraftEvoker implements Spawnable {

    static MaxRaid maxRaid;

    public RaidEvoker(MaxRaid raid) {
        super(raid.getHandle().getLevel().getCraftServer(), new NMSEvoker(raid));
        RaidEvoker.maxRaid = raid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Evoker");
    }

    public RaidEvoker(MaxRaid raid, BlockVector loc) {
        this(raid);
        int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
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
