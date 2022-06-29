package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftGoat;
import org.bukkit.util.BlockVector;

public class RaidGoat extends CraftGoat implements Spawnable {
    static MaxRaid maxRaid;

    public RaidGoat(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSGoat(maxRaid));
        RaidGoat.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Goat");
    }

    public RaidGoat(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    public static class NMSGoat extends Goat {
        MaxRaid raid;

        public NMSGoat(MaxRaid raid) {
            super(EntityType.GOAT, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
        }

        @Override
        public EntityDimensions getDimensions(Pose entitypose) {
            return EntityDimensions.scalable(10F, 10F);
        }

        @Override
        protected void registerGoals() {
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {

        }

    }

}
