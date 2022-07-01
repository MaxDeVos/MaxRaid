package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftWitch;
import org.bukkit.util.BlockVector;

public class RaidWitch extends CraftWitch implements Spawnable {

    static MaxRaid maxRaid;

    public RaidWitch(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSWitch(maxRaid));
        RaidWitch.maxRaid = maxRaid;
        setMaxHealth(30);
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Witch");
    }

    public RaidWitch(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSWitch extends Witch {
        MaxRaid raid;

        public NMSWitch(MaxRaid raid) {
            super(EntityType.WITCH, raid.getHandle().serverLevel);
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
