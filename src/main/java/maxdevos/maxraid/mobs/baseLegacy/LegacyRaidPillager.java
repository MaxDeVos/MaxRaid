package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidPillager extends LegacyRaidMob {

    public LegacyRaidPillager(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.PILLAGER);
    }

    public LegacyRaidPillager(Player target) {
        super(target, EntityType.PILLAGER);
    }

    @SuppressWarnings("unused")
    public LegacyRaidPillager(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.PILLAGER);
    }

    public LegacyRaidPillager(Location spawnLocation){
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
