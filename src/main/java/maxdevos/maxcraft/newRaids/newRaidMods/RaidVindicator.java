package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidVindicator extends RaidMob {

    public RaidVindicator(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.VINDICATOR);
    }

    public RaidVindicator(Player target) {
        super(target, EntityType.VINDICATOR);
    }

    public RaidVindicator(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.VINDICATOR);
    }

    public void setParams(LivingEntity e){
        Vindicator s = (Vindicator)e;
        s.setTarget(target);
        s.setMaxHealth(20);
        s.setHealth(20);
    }

}
