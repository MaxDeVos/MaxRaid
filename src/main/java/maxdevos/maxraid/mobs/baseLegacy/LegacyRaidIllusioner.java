package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidIllusioner extends LegacyRaidMob {

    public LegacyRaidIllusioner(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.ILLUSIONER);
    }

    public LegacyRaidIllusioner(Player target) {
        super(target, EntityType.ILLUSIONER);
    }

    @SuppressWarnings("unused")
    public LegacyRaidIllusioner(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.ILLUSIONER);
    }

    public LegacyRaidIllusioner(Location spawnLocation){
        super(spawnLocation, EntityType.ILLUSIONER);
    }

    public void setParams(LivingEntity e){
        Illusioner c = (Illusioner)e;
        c.setCustomName("§4Raid Illusioner");
        c.setCanJoinRaid(true);
        if(target != null){
            c.setTarget(target);
        }
        c.setMaxHealth(20);
        c.setHealth(20);
    }

}
