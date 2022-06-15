package maxdevos.maxraid.mobs.nmsMobs;

import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.raid.NMSRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftCreeper;

public class NMSCreeper extends Creeper {
    NMSRaid raid;
    CraftCreeper bukkitEntity; // To minimize usage of NMS, use the bukkit entity as much as possible

    public NMSCreeper(MaxRaid raid, Location location) {
        super(EntityType.CREEPER, raid.getHandle().serverLevel);
        bukkitEntity = (CraftCreeper) this.getBukkitEntity();
        bukkitEntity.setCustomName(ChatColor.DARK_RED + "NMSCreeper");
        bukkitEntity.setPowered(true);
    }


    @Override
    public void die(DamageSource damagesource) {
        super.die(damagesource);
        raid.raidMobs.remove(this);
        raid.updateBossbar();
    }

    public boolean hurt(DamageSource damagesource, float f) {
        raid.updateBossbar();
        return super.hurt(damagesource, f);
    }
}
