package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

public class RaidEnderman extends RaidMob {

    public RaidEnderman(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.ENDERMAN);
    }

    public RaidEnderman(Player target) {
        super(target, EntityType.ENDERMAN);
    }

    public RaidEnderman(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.ENDERMAN);
    }

    public void setParams(LivingEntity e){
        Enderman z = (Enderman)e;
        z.setTarget(target);
        z.setMaxHealth(20);
        z.setHealth(20);
    }

}
