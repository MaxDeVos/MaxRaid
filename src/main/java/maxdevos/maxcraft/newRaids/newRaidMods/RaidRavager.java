package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidRavager extends RaidMob {

    public RaidRavager(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.RAVAGER);
    }

    public RaidRavager(Player target) {
        super(target, EntityType.RAVAGER);
    }

    public RaidRavager(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.RAVAGER);
    }

    public void setParams(LivingEntity e){
        Ravager r = (Ravager)e;
        r.setTarget(target);
        r.setMaxHealth(20);
        r.setHealth(20);
    }

}
