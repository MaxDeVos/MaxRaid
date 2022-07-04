package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;

public class NMSSkeleton extends Skeleton implements RaidMob {
    MaxRaid raid;

    public NMSSkeleton(MaxRaid raid) {
        super(EntityType.SKELETON, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Skeleton");
    }

    public void registerRaidGoals() {

        goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new RangedBowAttackGoal<>(this, 2.0, 1, 25.0F));
        goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, Player.class, false));
        targetSelector.addGoal(3, new NearestAttackableMaxRaidTargetGoal<>(this, AbstractVillager.class, false));

    }

    /** Sunscreen */
    @Override
    protected boolean isSunBurnTick() {
        return false;
    }
}