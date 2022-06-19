package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftGhast;
import org.bukkit.util.BlockVector;

public class RaidGhast extends CraftGhast {

    static MaxRaid maxRaid;
    public RaidGhast(MaxRaid raid, BlockVector loc) {
        super(raid.getHandle().getLevel().getCraftServer(), new NMSGhast(raid));
        RaidGhast.maxRaid = raid;
        setCustomName(ChatColor.DARK_RED + "RAID Ghast");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        raid.getHandle().addMob(this.getHandle());
    }

    private static class NMSGhast extends Ghast {
        MaxRaid raid;
        public NMSGhast(MaxRaid raid) {
            super(EntityType.GHAST, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
        }

        @Override
        protected void registerGoals(){
//            goalSelector.removeAllGoals();
//            targetSelector.removeAllGoals();
        }

        /** Randomly floats around and targets Villagers and Players. Much potential, currently sucks. */
        protected void registerRaidGoals() {
            targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, AbstractVillager.class, false));
        }
    }

}
