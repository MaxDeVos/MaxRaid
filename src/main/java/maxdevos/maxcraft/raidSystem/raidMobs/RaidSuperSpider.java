package maxdevos.maxcraft.raidSystem.raidMobs;

import maxdevos.maxcraft.raidSystem.NMSMobs.NMSSpider;
import maxdevos.maxcraft.raidSystem.NMSMobs.SuicideCreeper;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidSuperSpider extends RaidMob {

    public RaidSuperSpider(Player target, RaidSpawnWaveEvent w) {
        super(target, w, new NMSSpider(target));
    }

    public RaidSuperSpider(Player target) {
        super(target, new NMSSpider(target));
    }

    @SuppressWarnings("unused")
    public RaidSuperSpider(Player target, Location spawnLocation) {
        super(target, spawnLocation, new NMSSpider(target));
    }

    public void setParams(LivingEntity e){
        Spider b = (Spider)e;
        b.setCustomName("§4Raid Super Spider");
        b.setTarget(target);
        b.setMaxHealth(20);
        b.setHealth(20);
    }

}
