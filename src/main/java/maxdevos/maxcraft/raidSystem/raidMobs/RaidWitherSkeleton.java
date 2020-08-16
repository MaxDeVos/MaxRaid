package maxdevos.maxcraft.raidSystem.raidMobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidWitherSkeleton extends RaidMob {

    public RaidWitherSkeleton(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.WITHER_SKELETON);
    }

    public RaidWitherSkeleton(Player target) {
        super(target, EntityType.WITHER_SKELETON);
    }

    @SuppressWarnings("unused")
    public RaidWitherSkeleton(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.WITHER_SKELETON);
    }

    public void setParams(LivingEntity e){
        WitherSkeleton s = (WitherSkeleton)e;
        s.setCustomName("§4Raid Wither Skeleton");
        s.setTarget(target);
        s.setMaxHealth(20);
        s.setHealth(20);
    }

}
