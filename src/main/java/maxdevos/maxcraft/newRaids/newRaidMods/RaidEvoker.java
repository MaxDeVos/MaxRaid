package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidEvoker extends RaidMob {

    public RaidEvoker(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.EVOKER);
    }

    public RaidEvoker(Player target) {
        super(target, EntityType.EVOKER);
    }

    @SuppressWarnings("unused")
    public RaidEvoker(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.EVOKER);
    }

    public void setParams(LivingEntity e){
        Evoker b = (Evoker)e;
        b.setCustomName("§4Raid Evoker");
        b.setCanJoinRaid(true);
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
