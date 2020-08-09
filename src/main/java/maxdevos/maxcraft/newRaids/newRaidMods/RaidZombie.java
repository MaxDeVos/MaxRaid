package maxdevos.maxcraft.newRaids.newRaidMods;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

@SuppressWarnings({"deprecation", "unused"})
public class RaidZombie extends RaidMob {

    public RaidZombie(Player target, RaidSpawnWaveEvent w) {
        super(target, w, EntityType.ZOMBIE);
    }

    public RaidZombie(Player target) {
        super(target, EntityType.ZOMBIE);
    }

    @SuppressWarnings("unused")
    public RaidZombie(Player target, Location spawnLocation) {
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
