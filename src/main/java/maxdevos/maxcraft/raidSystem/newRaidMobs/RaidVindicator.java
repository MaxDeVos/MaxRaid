package maxdevos.maxcraft.raidSystem.newRaidMobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidVindicator extends RaidMob {

    public RaidVindicator(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.VINDICATOR);
    }

    public RaidVindicator(Player target) {
        super(target, EntityType.VINDICATOR);
    }

    @SuppressWarnings("unused")
    public RaidVindicator(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.VINDICATOR);
    }

    public void setParams(LivingEntity e){
        Vindicator s = (Vindicator)e;
        s.setCanJoinRaid(true);
        s.setCustomName("§4Raid Vindicator");
        s.setTarget(target);
        s.setMaxHealth(20);
        s.setHealth(20);
    }

}
