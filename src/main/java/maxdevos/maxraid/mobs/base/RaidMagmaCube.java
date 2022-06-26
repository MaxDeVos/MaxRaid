package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.MagmaCube;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMagmaCube;
import org.bukkit.util.BlockVector;

/** Doesn't work. This shit is borked*/
@Deprecated
public class RaidMagmaCube extends CraftMagmaCube {

    static MaxRaid maxRaid;
    public RaidMagmaCube(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSMagmaCube(maxRaid));
        RaidMagmaCube.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID MagmaCube");
    }

    public RaidMagmaCube(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        spawn(loc);
    }

    public void spawn(BlockVector loc){
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }
    private static class NMSMagmaCube extends MagmaCube {
        MaxRaid raid;
        public NMSMagmaCube(MaxRaid raid) {
            super(EntityType.MAGMA_CUBE, raid.getHandle().serverLevel);
            setSize(6, true);
            this.raid = raid;
            registerRaidGoals();
        }

//        @Override
//        protected void registerGoals(){
//            goalSelector.removeAllGoals();
//            targetSelector.removeAllGoals();
//        }

        protected void registerRaidGoals() {
//            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, 10, true, false,
//                    (entityliving) -> Math.abs(((LivingEntity) entityliving).getY() - this.getY()) <= 4.0));
//            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, AbstractVillager.class, 10, true, false,
//                    (entityliving) -> Math.abs(((LivingEntity) entityliving).getY() - this.getY()) <= 4.0));
        }
    }

}
