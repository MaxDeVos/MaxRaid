package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.LookAtPointGoal;
import maxdevos.maxraid.goals.MaxRaidBlazeAttackGoal;
import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.goals.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftBlaze;
import org.bukkit.util.BlockVector;

public class RaidBlaze extends CraftBlaze {
    static MaxRaid maxRaid;
    public RaidBlaze(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSBlaze(maxRaid));
        RaidBlaze.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "RAID Blaze");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    public static class NMSBlaze extends Blaze {
        MaxRaid raid;
        public NMSBlaze(MaxRaid raid) {
            super(EntityType.BLAZE, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
        }

        @Override
        protected void registerGoals(){
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {
            //TODO make this mob-specific
            goalSelector.addGoal(1, new FloatGoal(this));
            goalSelector.addGoal(2, new MaxRaidBlazeAttackGoal(this));
            goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));
            goalSelector.addGoal(4, new LookAtPointGoal(this, raid.getVillageCenter()));

            targetSelector.addGoal(1, new HurtByTargetGoal(this));
            targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, Player.class, false));
            targetSelector.addGoal(3, new NearestAttackableMaxRaidTargetGoal<>(this, AbstractVillager.class, false));

        }

        public void setCharged(boolean var0){
            EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Blaze.class, EntityDataSerializers.BYTE);
            byte var1 = this.entityData.get(DATA_FLAGS_ID);
            if (var0) {
                var1 = (byte)(var1 | 1);
            } else {
                var1 &= -2;
            }

            this.entityData.set(DATA_FLAGS_ID, var1);
        }
    }

}
