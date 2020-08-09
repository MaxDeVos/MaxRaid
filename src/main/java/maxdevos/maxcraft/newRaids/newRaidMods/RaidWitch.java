package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings({"deprecation", "unused"})
public class RaidWitch extends RaidMob {

    public RaidWitch(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.WITCH);
    }

    public RaidWitch(Player target) {
        super(target, EntityType.WITCH);
    }

    @SuppressWarnings("unused")
    public RaidWitch(Player target, Location spawnLocation) {
        super(target, spawnLocation, EntityType.WITCH);
    }

    public void setParams(LivingEntity e){
        Witch c = (Witch)e;
        c.setCustomName("§4Raid Witch");
        c.setCanJoinRaid(true);
        c.setTarget(target);
        c.setMaxHealth(20);
        c.setHealth(20);
    }

}
