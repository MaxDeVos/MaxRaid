package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftZombie;
import org.bukkit.util.BlockVector;

public class VillageTourZombie extends CraftZombie implements Spawnable {

    static MaxRaid maxRaid;

    public VillageTourZombie(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSZombie(maxRaid));
        VillageTourZombie.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "Village Touring Zombie");
    }

    public VillageTourZombie(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSZombie extends Zombie {
        private final MaxRaid raid;

        public NMSZombie(MaxRaid raid) {
            super(EntityType.ZOMBIE, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
        }

        @Override
        protected void registerGoals() {
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {
            goalSelector.addGoal(1, new FloatGoal(this));
            goalSelector.addGoal(2, new ZombieAttackGoal(this, 2.0, true));
            goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 2.0, false, 4, this::canBreakDoors));

            targetSelector.addGoal(1, new HurtByTargetGoal(this));
            targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, Player.class, false));
            targetSelector.addGoal(3, new NearestAttackableMaxRaidTargetGoal<>(this, AbstractVillager.class, false));
        }

        /**
         * Make immune from sunburn
         */
        @Override
        protected boolean isSunSensitive() {
            return false;
        }
    }

}
