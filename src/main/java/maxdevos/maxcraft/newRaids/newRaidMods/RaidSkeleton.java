package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidSkeleton extends RaidMob {

    public RaidSkeleton(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.SKELETON);
    }

    public RaidSkeleton(Player target) {
        super(target, EntityType.SKELETON);
    }

    public RaidSkeleton(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.SKELETON);
    }

    public void setParams(LivingEntity e){
        Skeleton s = (Skeleton)e;
        s.setTarget(target);
        s.setMaxHealth(20);
        s.setHealth(20);
    }

}
