package maxdevos.maxraid.mobs.base;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidMagmaCube extends RaidMob {

    public RaidMagmaCube(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.MAGMA_CUBE);
    }

    public RaidMagmaCube(Player target) {
        super(target, EntityType.MAGMA_CUBE);
    }

    @SuppressWarnings("unused")
    public RaidMagmaCube(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.MAGMA_CUBE);
    }

    public void setParams(LivingEntity e){
        MagmaCube b = (MagmaCube)e;
        b.setCustomName("§4Raid Magma Cube");
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
