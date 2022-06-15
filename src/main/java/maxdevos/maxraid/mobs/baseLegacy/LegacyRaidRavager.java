package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Ravager;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidRavager extends LegacyRaidMob {

    public LegacyRaidRavager(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.RAVAGER);
    }

    public LegacyRaidRavager(Player target) {
        super(target, EntityType.RAVAGER);
    }

    @SuppressWarnings("unused")
    public LegacyRaidRavager(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.RAVAGER);
    }

    public LegacyRaidRavager(Location spawnLocation){
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
