package maxdevos.maxcraft.raidSystem.newRaidMobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidSkeleton extends RaidMob {

    public RaidSkeleton(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.SKELETON);
    }

    public RaidSkeleton(Player target) {
        super(target, EntityType.SKELETON);
    }

    @SuppressWarnings("unused")
    public RaidSkeleton(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.SKELETON);
    }

    public void setParams(LivingEntity e){
        Skeleton s = (Skeleton)e;
        s.setCustomName("§4Raid Skeleton");
        s.setTarget(target);
        s.setMaxHealth(20);
        s.setHealth(20);

        this.giveLeatherHelmet();

    }

}
