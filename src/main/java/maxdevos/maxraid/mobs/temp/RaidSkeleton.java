package maxdevos.maxraid.mobs.temp;

import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.items.Equipper;
import maxdevos.maxraid.items.armor.RaidArmor;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftSkeleton;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockVector;

public class RaidSkeleton extends CraftSkeleton implements Spawnable {

    static MaxRaid maxRaid;

    public RaidSkeleton(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSSkeleton(maxRaid));
        RaidSkeleton.maxRaid = maxRaid;
        setPersistent(true);
        setCustomName(ChatColor.DARK_RED + "RAID Skeleton");

        ItemStack weapon = new ItemStack(Material.BOW, 1);
        weapon.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        weapon.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        this.getEquipment().setItemInMainHand(weapon);
    }

    public RaidSkeleton(MaxRaid maxRaid, BlockVector loc, float health) {
        this(maxRaid);
        int y = maxRaid.getHandle().getLevel().getHeight(Heightmap.Types.MOTION_BLOCKING, loc.getBlockX(), loc.getBlockZ());
        loc = new BlockVector(loc.getX(), y, loc.getZ());
        getHandle().getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("raid bonus", health, AttributeModifier.Operation.ADDITION));
        getHandle().setHealth(health);
        spawn(loc);
    }

    public RaidSkeleton(MaxRaid raid, BlockVector loc, float health, Color color, ItemStack weapon){
        this(raid, loc, health);

        if (color != null){
            Equipper.setMobArmor(this, new RaidArmor(color));
        }

        Equipper.setMobWeapon(this, weapon);
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
            //TODO better aiming attack (rapid-fire full power shots)
            goalSelector.addGoal(2, new RangedBowAttackGoal<>(this, 2.0, 1, 25.0F));
            goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));

            targetSelector.addGoal(1, new HurtByTargetGoal(this));
            targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, Player.class, false));
            targetSelector.addGoal(3, new NearestAttackableMaxRaidTargetGoal<>(this, AbstractVillager.class, false));
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
