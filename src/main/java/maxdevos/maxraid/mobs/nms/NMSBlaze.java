package maxdevos.maxraid.mobs.nms;

import maxdevos.maxraid.goals.MaxRaidBlazeAttackGoal;
import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;

public class NMSBlaze extends Blaze implements RaidMob {

    MaxRaid raid;

    public NMSBlaze(MaxRaid raid) {
        super(EntityType.BLAZE, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Blaze");
    }

    @Override
    protected void registerGoals() {
        goalSelector.removeAllGoals();
        targetSelector.removeAllGoals();
    }

    public void registerRaidGoals() {

        goalSelector.addGoal(1, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 2.0));

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, Player.class, false));
        targetSelector.addGoal(3, new NearestAttackableMaxRaidTargetGoal<>(this, AbstractVillager.class, false));

    }

    public void setCharged(boolean var0) {
        EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Blaze.class, EntityDataSerializers.BYTE);
        byte var1 = this.entityData.get(DATA_FLAGS_ID);
        if (var0) {
            var1 = (byte) (var1 | 1);
        } else {
            var1 &= -2;
        }

        this.entityData.set(DATA_FLAGS_ID, var1);
    }

}
