package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.*;
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
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftCreeper;
import org.bukkit.util.BlockVector;

public class WallSeekingCreeper extends CraftCreeper {

    static MaxRaid maxRaid;
    public WallSeekingCreeper(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSCreeper(maxRaid));
        WallSeekingCreeper.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "SUICIDE CREEPER");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSCreeper extends Creeper {
        MaxRaid raid;
        public NMSCreeper(MaxRaid raid) {
            super(EntityType.CREEPER, raid.getHandle().serverLevel);
            this.raid = raid;
            this.explosionRadius = 5;
            registerRaidGoals();
        }

        @Override
        protected void registerGoals(){
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {

            goalSelector.addGoal(1, new FloatGoal(this));
            goalSelector.addGoal(2, new CreeperExplodeAgainstWallGoal((CraftCreeper) this.getBukkitEntity()));
            goalSelector.addGoal(3, new CreeperPathfindToRaidWall(this, this.raid));
            //            goalSelector.addGoal(4, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 2.0));
//            goalSelector.addGoal(5, new LookAtPointGoal(this, raid.getVillageCenter()));

//            targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
//            targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
//            targetSelector.addGoal(3, new HurtByTargetGoal(this));

        }
    }

}
