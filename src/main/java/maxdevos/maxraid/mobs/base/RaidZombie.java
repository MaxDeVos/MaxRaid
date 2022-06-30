package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.items.Equipper;
import maxdevos.maxraid.items.RaidItemType;
import maxdevos.maxraid.items.armor.RaidArmor;
import maxdevos.maxraid.items.weapons.swords.RaidSword;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftZombie;
import org.bukkit.util.BlockVector;

public class RaidZombie extends CraftZombie implements Spawnable {

    static MaxRaid maxRaid;

    public RaidZombie(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSZombie(maxRaid));
        RaidZombie.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Zombie");

        Equipper.setMobWeapon(this, new RaidSword(RaidItemType.WeaponMaterial.DIAMOND));
        Equipper.setMobArmor(this, new RaidArmor(Color.GRAY));
    }

    public RaidZombie(MaxRaid maxRaid, float health, RaidSword sword, float speed) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSZombie(maxRaid));
        RaidZombie.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Zombie");

        Equipper.setMobWeapon(this, new RaidSword(RaidItemType.WeaponMaterial.DIAMOND));
        Equipper.setMobArmor(this, new RaidArmor(Color.GRAY));
    }

    public RaidZombie(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        spawn(loc);
    }

    public void spawn(BlockVector loc) {
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
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
            goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));

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
