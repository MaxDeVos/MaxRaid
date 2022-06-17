package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.mobs.goals.LookAtPointGoal;
import maxdevos.maxraid.mobs.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftCreeper;
import org.bukkit.util.BlockVector;

public class FuselessCreeper extends CraftCreeper {

    static MaxRaid maxRaid;
    public FuselessCreeper(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSCreeper(maxRaid));
        FuselessCreeper.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "FUSELESS CREEPER");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSCreeper extends Creeper {
        MaxRaid raid;
        public NMSCreeper(MaxRaid raid) {
            super(EntityType.CREEPER, raid.getHandle().serverLevel);
            this.raid = raid;
            this.maxSwell = 2;
            registerRaidGoals();
        }

        @Override
        protected void registerGoals(){
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {

            goalSelector.addGoal(1, new FloatGoal(this));
            goalSelector.addGoal(2, new SwellGoal(this));
            goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0, false));
            goalSelector.addGoal(4, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));
            goalSelector.addGoal(5, new LookAtPointGoal(this, raid.getVillageCenter()));

            targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
            targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
            targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[0]));

        }
    }

}
