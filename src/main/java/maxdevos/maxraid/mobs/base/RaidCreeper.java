package maxdevos.maxraid.mobs.base;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidCreeper extends RaidMob {

    public RaidCreeper(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.CREEPER);
    }

    public RaidCreeper(Player target) {
        super(target, EntityType.CREEPER);
    }

    @SuppressWarnings("unused")
    public RaidCreeper(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.CREEPER);
    }

    public void setParams(LivingEntity e){
        Creeper c = (Creeper)e;
        c.setCustomName("§4Raid Creeper");
        c.setTarget(target);
        c.setMaxHealth(20);
        c.setHealth(20);
    }

}
