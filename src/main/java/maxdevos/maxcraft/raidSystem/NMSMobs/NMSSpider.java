package maxdevos.maxcraft.raidSystem.NMSMobs;

import net.minecraft.server.v1_16_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.entity.EntityTargetEvent;

public class NMSSpider extends EntitySpider {

    public NMSSpider(Player p) {
        super(EntityTypes.SPIDER, ((CraftWorld)p.getWorld()).getHandle());
        this.setPosition(p.getLocation().getX(),p.getLocation().getY()+10,p.getLocation().getZ());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_RED + "NMSZombie"));
        this.setGoalTarget((((CraftPlayer)p).getHandle()), EntityTargetEvent.TargetReason.CUSTOM, true);
        this.setAggressive(true);

        ((Spider) this.getBukkitEntity()).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5d);

    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0,new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 2.0f, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 2.0D));
    }

    protected float b(EntityPose entitypose, EntitySize entitysize) {
        return 0.2f;
    }
}
