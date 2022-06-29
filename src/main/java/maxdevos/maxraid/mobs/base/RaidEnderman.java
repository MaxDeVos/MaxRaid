package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.LookAtPointGoal;
import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEnderman;
import org.bukkit.util.BlockVector;

public class RaidEnderman extends CraftEnderman implements Spawnable {

    static MaxRaid maxRaid;

    public RaidEnderman(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSEnderman(maxRaid));
        RaidEnderman.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Enderman");
    }

    public RaidEnderman(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSEnderman extends EnderMan {
        MaxRaid raid;

        public NMSEnderman(MaxRaid raid) {
            super(EntityType.ENDERMAN, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
        }

        @Override
        protected void registerGoals() {
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        /**
         * Currently mostly random, will attack villagers and randomly meander around.
         * Pretty ineffective in battle at the moment, to be honest. Has much potential.
         */
        protected void registerRaidGoals() {

            goalSelector.addGoal(1, new FloatGoal(this));
            goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
            goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));

            targetSelector.addGoal(1, new HurtByTargetGoal(this));
            targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
            targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));

        }
    }
}
