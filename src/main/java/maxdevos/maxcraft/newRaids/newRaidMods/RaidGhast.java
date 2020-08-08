package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
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
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
