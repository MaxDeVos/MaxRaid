package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidPhantom extends RaidMob {

    public RaidPhantom(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.PHANTOM);
    }

    public RaidPhantom(Player target) {
        super(target, EntityType.PHANTOM);
    }

    public RaidPhantom(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.PHANTOM);
    }

    public void setParams(LivingEntity e){
        Phantom b = (Phantom)e;
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
