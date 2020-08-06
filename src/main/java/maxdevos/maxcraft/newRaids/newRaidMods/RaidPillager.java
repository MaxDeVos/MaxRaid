package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidPillager extends RaidMob {

    public RaidPillager(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.PILLAGER);
    }

    public RaidPillager(Player target) {
        super(target, EntityType.PILLAGER);
    }

    public RaidPillager(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.PILLAGER);
    }

    public void setParams(LivingEntity e){
        Pillager p = (Pillager)e;
        p.setTarget(target);
        p.setMaxHealth(20);
        p.setHealth(20);
    }

}
