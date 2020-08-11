package maxdevos.maxcraft.raidSystem.newRaidMobs;

import maxdevos.maxcraft.raidSystem.NMSMobs.CreeperStuckAgainstWall;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

public class SuicideCreeper extends EntityCreeper {

    public SuicideCreeper(Player p) {
        super(EntityTypes.CREEPER, ((CraftWorld)p.getWorld()).getHandle());
        this.setPosition(p.getLocation().getX(),p.getLocation().getY()+10,p.getLocation().getZ());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_RED + "NMSCreeper"));
        this.setGoalTarget((((CraftPlayer)p).getHandle()), EntityTargetEvent.TargetReason.CUSTOM, true);
        this.setAggressive(true);
        Creeper c = ((Creeper) this.getBukkitEntity());
        c.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5d);
        generateNotifierEffect(c.getLocation().add(new Vector(0,10,0)));
    }

    private void generateNotifierEffect(Location l){
        Location fireworkLocation = l.add(0,1,0);
        fireworkLocation.getWorld().spawnEntity(fireworkLocation, EntityType.FIREWORK);

        Firework fw = (Firework) fireworkLocation.getWorld().spawnEntity(fireworkLocation, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(5);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.BLACK).flicker(false).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0,new PathfinderGoalFloat(this));
//        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 2.0f, false));
//        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.goalSelector.a(3, new CreeperStuckAgainstWall(this));
    }

    protected float b(EntityPose entitypose, EntitySize entitysize) {
        return 0.2f;
    }
}
