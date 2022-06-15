package maxdevos.maxraid.mobs.baseLegacy;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class LegacyRaidZombie extends LegacyRaidMob {

    public LegacyRaidZombie(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.ZOMBIE);
    }

    public LegacyRaidZombie(Player target) {
        super(target, EntityType.ZOMBIE);
    }

    @SuppressWarnings("unused")
    public LegacyRaidZombie(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.ZOMBIE);
    }

    public void setParams(LivingEntity e){

        Zombie z = (Zombie)e;
        z.setCustomName("§4Raid Zombie");
        z.setTarget(target);
        z.setMaxHealth(20);
        z.setHealth(20);

        this.giveLeatherHelmet();

    }

}
