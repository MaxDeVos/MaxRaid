package maxdevos.maxcraft.raidSystem.newRaidMobs;

import maxdevos.maxcraft.raidSystem.NMSMobs.SuicideCreeper;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidSuicideCreeper extends RaidMob {

    public RaidSuicideCreeper(Player target, RaidSpawnWaveEvent w) {
        super(target, w, new SuicideCreeper(target));
    }

    public RaidSuicideCreeper(Player target) {
        super(target, new SuicideCreeper(target));
    }

    @SuppressWarnings("unused")
    public RaidSuicideCreeper(Player target, Location spawnLocation) {
        super(target, spawnLocation, new SuicideCreeper(target));
    }

    public void setParams(LivingEntity e){
        Creeper b = (Creeper)e;
        b.setCustomName("§4Raid Suicide Creeper");
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
