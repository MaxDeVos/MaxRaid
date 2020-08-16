package maxdevos.maxcraft.raidSystem.raidMobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidRavager extends RaidMob {

    public RaidRavager(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.RAVAGER);
    }

    public RaidRavager(Player target) {
        super(target, EntityType.RAVAGER);
    }

    @SuppressWarnings("unused")
    public RaidRavager(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.RAVAGER);
    }

    public RaidRavager(Location spawnLocation){
        super(spawnLocation, EntityType.RAVAGER);
    }

    public void setParams(LivingEntity e){
        Ravager r = (Ravager)e;
        r.setCustomName("§4Raid Ravager");
        if(target != null){
            r.setTarget(target);
        }
        r.setMaxHealth(20);
        r.setHealth(20);
    }

}
