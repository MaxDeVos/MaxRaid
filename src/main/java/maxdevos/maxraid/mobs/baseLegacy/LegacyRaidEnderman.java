package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidEnderman extends LegacyRaidMob {


    public LegacyRaidEnderman(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.ENDERMAN);
    }

    public LegacyRaidEnderman(Player target) {
        super(target, EntityType.ENDERMAN);
    }

    @SuppressWarnings("unused")
    public LegacyRaidEnderman(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.ENDERMAN);
    }

    public void setParams(LivingEntity e){
        Enderman z = (Enderman)e;
        z.setCustomName("§4Raid Enderman");
        z.setTarget(target);
        z.setMaxHealth(20);
        z.setHealth(20);
    }

}
