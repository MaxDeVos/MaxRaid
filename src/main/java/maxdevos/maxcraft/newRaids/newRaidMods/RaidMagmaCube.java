package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidMagmaCube extends RaidMob {

    public RaidMagmaCube(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.MAGMA_CUBE);
    }

    public RaidMagmaCube(Player target) {
        super(target, EntityType.MAGMA_CUBE);
    }

    public RaidMagmaCube(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.MAGMA_CUBE);
    }

    public void setParams(LivingEntity e){
        MagmaCube b = (MagmaCube)e;
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
