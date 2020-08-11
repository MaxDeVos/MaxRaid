package maxdevos.maxcraft.raidSystem.NMSMobs;

import net.minecraft.server.v1_16_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Raider;
import org.bukkit.event.entity.EntityTargetEvent;

public class NMSZombie extends EntityZombie {

    public NMSZombie(Player p) {
        super(((CraftWorld)p.getWorld()).getHandle());
        this.setPosition(p.getLocation().getX(),p.getLocation().getY()+10,p.getLocation().getZ());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_RED + "NMSZombie"));
        this.setGoalTarget((((CraftPlayer)p).getHandle()), EntityTargetEvent.TargetReason.CUSTOM, true);
        this.setAggressive(true);
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0,new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalZombieAttack(this, 2f, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.goalSelector.a(4,new CustomMeleeAttack(this));
        this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 2.0D));
    }
}
