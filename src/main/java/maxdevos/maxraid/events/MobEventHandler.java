package maxdevos.maxraid.events;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMob;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.*;

public class MobEventHandler implements Listener {

    private MaxRaid raid;
    private final RaidPlugin plugin = RaidPlugin.getInstance();

    public MobEventHandler(MaxRaid raid){
        this.raid = raid;
        Server server = RaidPlugin.getServerInstance();
        server.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void antiRaidMobFallDamage(EntityDamageEvent e){
        if(!((e.getEntity()) instanceof Player) && e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void antiRaidMobFriendlyFire(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player) && !(e.getEntity() instanceof AbstractVillager)){
            if(e.getDamager() instanceof CraftMob){
                e.setCancelled(true);
            }
            if (e.getDamager() instanceof AbstractArrow a) {
                if (a.getShooter() instanceof CraftMob) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void antiEarthExplosion(EntityExplodeEvent e){
        for(Block b:e.blockList()){
            if(b.getType().equals(Material.DIRT) || b.getType().equals(Material.GRASS_BLOCK) ||
                    b.getType().equals(Material.PODZOL) || b.getType().equals(Material.STONE)){
                e.blockList().remove(b);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void antiEarthExplosion(BlockExplodeEvent e){
        for(Block b:e.blockList()){
            if(b.getType().equals(Material.DIRT) || b.getType().equals(Material.GRASS_BLOCK) ||
                    b.getType().equals(Material.PODZOL) || b.getType().equals(Material.STONE)){
                e.blockList().remove(b);
            }
        }
    }

    @EventHandler
    private void antiVex2(EntitySpawnEvent e){
        if(e.getEntityType().equals(EntityType.VEX)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity ent = event.getEntity();

        if (ent instanceof Fireball) {
            event.setCancelled(true);
            plugin.getServer().getWorlds().get(0).createExplosion(ent.getLocation(), 3);
        }
    }

    public void unregister(){
        HandlerList.unregisterAll(this);
    }
}
