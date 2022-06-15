package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidSkeleton extends LegacyRaidMob {

    public LegacyRaidSkeleton(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.SKELETON);
    }

    public LegacyRaidSkeleton(Player target) {
        super(target, EntityType.SKELETON);
    }

    @SuppressWarnings("unused")
    public LegacyRaidSkeleton(Player target, Location spawnLocation) {
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
