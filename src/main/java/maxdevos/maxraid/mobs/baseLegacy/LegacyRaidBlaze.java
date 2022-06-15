package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidBlaze extends LegacyRaidMob {

    public LegacyRaidBlaze(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.BLAZE);
    }

    public LegacyRaidBlaze(Player target) {
        super(target, EntityType.BLAZE);
    }

    @SuppressWarnings("unused")
    public LegacyRaidBlaze(Player target, Location spawnLocation) {
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
