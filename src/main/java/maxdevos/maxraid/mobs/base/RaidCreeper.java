package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.LookAtPointGoal;
import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftCreeper;
import org.bukkit.util.BlockVector;

public class RaidCreeper extends CraftCreeper implements Spawnable {

    static MaxRaid maxRaid;

    public RaidCreeper(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSCreeper(maxRaid));
        this.setPersistent(true);
        RaidCreeper.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID CREEPER");
    }

    public RaidCreeper(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSCreeper extends Creeper {
        MaxRaid raid;

        public NMSCreeper(MaxRaid raid) {
            super(EntityType.CREEPER, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
        }

        @Override
        public float getScale() {
            return 10F;
        }

        @Override
        protected void registerGoals() {
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {

            goalSelector.addGoal(1, new FloatGoal(this));
            goalSelector.addGoal(2, new SwellGoal(this));
            goalSelector.addGoal(3, new MeleeAttackGoal(this, 2f, false));
            goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.5f));

            targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
            targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
            targetSelector.addGoal(3, new HurtByTargetGoal(this));

        }
    }

}
