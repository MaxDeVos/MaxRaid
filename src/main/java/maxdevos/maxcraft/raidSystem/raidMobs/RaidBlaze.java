package maxdevos.maxcraft.raidSystem.raidMobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidBlaze extends RaidMob {

    public RaidBlaze(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.BLAZE);
    }

    public RaidBlaze(Player target) {
        super(target, EntityType.BLAZE);
    }

    @SuppressWarnings("unused")
    public RaidBlaze(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.BLAZE);
    }

    public void setParams(LivingEntity e){
        Blaze b = (Blaze)e;
        b.setCustomName("§4Raid Blaze");
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
