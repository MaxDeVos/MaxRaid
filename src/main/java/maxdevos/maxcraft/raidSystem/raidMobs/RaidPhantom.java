package maxdevos.maxcraft.raidSystem.raidMobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidPhantom extends RaidMob {

    public RaidPhantom(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.PHANTOM);
    }

    public RaidPhantom(Player target) {
        super(target, EntityType.PHANTOM);
    }

    @SuppressWarnings("unused")
    public RaidPhantom(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.PHANTOM);
    }

    public void setParams(LivingEntity e){
        Phantom b = (Phantom)e;
        b.setCustomName("§4Raid Phantom");
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
