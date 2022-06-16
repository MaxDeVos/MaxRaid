package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.mobs.goals.NearestAttackableVillagerGoal;
import maxdevos.maxraid.mobs.goals.SpiderAttackGoal;
import maxdevos.maxraid.mobs.goals.SpiderTargetGoal;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftSpider;
import org.bukkit.util.BlockVector;

import java.util.function.BooleanSupplier;
import java.util.logging.Level;

public class RaidSpider extends CraftSpider {

    //TODO THIS FUCKER COMES TO LIFE *AFTER THE RAID* WHY???????
    static MaxRaid maxRaid;
    public RaidSpider(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSSpider(maxRaid));
        RaidSpider.maxRaid = maxRaid;

        setCustomName(ChatColor.DARK_RED + "RAID Spider");
        getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());

        maxRaid.addMob(this);
    }

    private static class NMSSpider extends Spider {
        public NMSSpider(MaxRaid raid) {
            super(EntityType.SPIDER, raid.getHandle().serverLevel);
        }
        @Override
        protected void registerGoals() {
            super.registerGoals();
            targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        }
    }



}
