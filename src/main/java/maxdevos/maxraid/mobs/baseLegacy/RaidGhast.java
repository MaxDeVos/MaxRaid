package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.util.Vector;

@SuppressWarnings({"deprecation", "unused"})
public class RaidGhast extends RaidMob {

    public RaidGhast(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.GHAST);
    }

    public RaidGhast(Player target) {
        super(target, target.getLocation().add(new Vector(0,30,0)), EntityType.GHAST);
    }

    @SuppressWarnings("unused")
    public RaidGhast(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.GHAST);
    }

    public void setParams(LivingEntity e){
        Ghast b = (Ghast)e;
        b.setCustomName("§4Raid Ghast");
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
