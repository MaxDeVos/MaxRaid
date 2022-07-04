package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;

public class NMSCreeper extends Creeper implements RaidMob {
    MaxRaid raid;

    public NMSCreeper(MaxRaid raid) {
        super(EntityType.CREEPER, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Creeper");
    }

    public void registerRaidGoals() {

        goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new SwellGoal(this));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 2f, false));
        goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.5f));

        targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
        targetSelector.addGoal(3, new HurtByTargetGoal(this));

    }
}