package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidWitherSkeleton extends LegacyRaidMob {

    public LegacyRaidWitherSkeleton(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.WITHER_SKELETON);
    }

    public LegacyRaidWitherSkeleton(Player target) {
        super(target, EntityType.WITHER_SKELETON);
    }

    @SuppressWarnings("unused")
    public LegacyRaidWitherSkeleton(Player target, Location spawnLocation) {
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
