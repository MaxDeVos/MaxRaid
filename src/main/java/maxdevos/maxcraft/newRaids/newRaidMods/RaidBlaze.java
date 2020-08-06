package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidBlaze extends RaidMob {

    public RaidBlaze(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.BLAZE);
    }

    public RaidBlaze(Player target) {
        super(target, EntityType.BLAZE);
    }

    public RaidBlaze(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.BLAZE);
    }

    public void setParams(LivingEntity e){
        Blaze b = (Blaze)e;
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
