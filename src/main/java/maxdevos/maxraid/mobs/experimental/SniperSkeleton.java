package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.SniperSearchAndAim;
import maxdevos.maxraid.items.Equipper;
import maxdevos.maxraid.items.RaidItemType;
import maxdevos.maxraid.items.armor.RaidArmor;
import maxdevos.maxraid.items.weapons.bows.RaidBow;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftSkeleton;
import org.bukkit.util.BlockVector;

public class SniperSkeleton extends CraftSkeleton implements Spawnable {

    static MaxRaid maxRaid;

    public SniperSkeleton(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSSkeleton(maxRaid));
        SniperSkeleton.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "Sniper Skeleton");

        Equipper.setMobWeapon(this, new RaidBow());
        Equipper.setMobArmor(this, new RaidArmor(Color.AQUA));
    }

    public SniperSkeleton(MaxRaid raid, BlockVector loc) {
        this(raid);
        int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSSkeleton extends Skeleton {
        MaxRaid raid;

        public NMSSkeleton(MaxRaid raid) {
            super(EntityType.SKELETON, raid.getHandle().serverLevel);
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
            goalSelector.addGoal(2, new SniperSearchAndAim<>(this, raid, 30, Player.class));
            goalSelector.addGoal(2, new SniperSearchAndAim<>(this, raid, 30, AbstractVillager.class));

//            goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));
//            goalSelector.addGoal(4, new LookAtPointGoal(this, raid.getVillageCenter()));
//
//            targetSelector.addGoal(1, new HurtByTargetGoal(this));
//            targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, Player.class, 200));
        }

        /**
         * Make immune from sunburn
         */
        @Override
        protected boolean isSunBurnTick() {
            return false;
        }
    }

}
