package maxdevos.maxcraft.raidSystem.raidMobs;

import maxdevos.maxcraft.raidSystem.NMSMobs.NMSZombie;
import maxdevos.maxcraft.raidSystem.NMSMobs.SuicideCreeper;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidSuperZombie extends RaidMob {

    public RaidSuperZombie(Player target, RaidSpawnWaveEvent w) {
        super(target, w, new NMSZombie(target));
    }

    public RaidSuperZombie(Player target) {
        super(target, new NMSZombie(target));
    }

    @SuppressWarnings("unused")
    public RaidSuperZombie(Player target, Location spawnLocation) {
        super(target, spawnLocation, new NMSZombie(target));
    }

    public void setParams(LivingEntity e){
        Zombie b = (Zombie)e;
        b.setCustomName("§4Raid Super Zombie");
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
