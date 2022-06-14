package maxdevos.maxraid.mobs.base;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidIllusioner extends RaidMob {

    public RaidIllusioner(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.ILLUSIONER);
    }

    public RaidIllusioner(Player target) {
        super(target, EntityType.ILLUSIONER);
    }

    @SuppressWarnings("unused")
    public RaidIllusioner(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.ILLUSIONER);
    }

    public RaidIllusioner(Location spawnLocation){
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
