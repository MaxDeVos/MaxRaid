package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidVindicator extends LegacyRaidMob {

    public LegacyRaidVindicator(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.VINDICATOR);
    }

    public LegacyRaidVindicator(Player target) {
        super(target, EntityType.VINDICATOR);
    }

    @SuppressWarnings("unused")
    public LegacyRaidVindicator(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.VINDICATOR);
    }

    public LegacyRaidVindicator(Location spawnLocation){
        super(spawnLocation, EntityType.VINDICATOR);
    }

    public void setParams(LivingEntity e){
        Vindicator s = (Vindicator)e;
        s.setCanJoinRaid(true);
        s.setCustomName("§4Raid Vindicator");
        if(target != null){
            s.setTarget(target);
        }
        s.setMaxHealth(20);
        s.setHealth(20);
    }

}
