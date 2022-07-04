package maxdevos.maxraid.mobs.temp;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftRavager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockVector;

public class RaidRavager extends CraftRavager implements Spawnable {

    static MaxRaid maxRaid;

    public RaidRavager(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSRavager(maxRaid));
        RaidRavager.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Ravager");
        getEquipment().setItemInMainHand(new ItemStack(Material.CROSSBOW));
    }

    public RaidRavager(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
                int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
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
