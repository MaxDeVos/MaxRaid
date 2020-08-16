package maxdevos.maxcraft.raidSystem.raidMobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidPillager extends RaidMob {

    public RaidPillager(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.PILLAGER);
    }

    public RaidPillager(Player target) {
        super(target, EntityType.PILLAGER);
    }

    @SuppressWarnings("unused")
    public RaidPillager(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.PILLAGER);
    }

    public RaidPillager(Location spawnLocation){
        super(spawnLocation, EntityType.PILLAGER);
    }

    public void setParams(LivingEntity e){
        Pillager p = (Pillager)e;
        p.setCustomName("§4Raid Pillager");
        p.setCanJoinRaid(true);
        if(target != null){
            p.setTarget(target);
        }
        p.setMaxHealth(20);
        p.setHealth(20);
    }

}
