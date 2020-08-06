package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class RaidEventHandler implements Listener {

    MaxPlugin plugin;

    RaidEventHandler(MaxPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void friendlyFire(EntityTargetEvent e) {
        if (!(e instanceof Player) && !(e.getTarget() instanceof Player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void antiExplosion(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void antiVex(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) {
            if(e.getDamager().getType().equals(EntityType.VEX)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void antiVex2(EntitySpawnEvent e){
        if(e.getEntityType().equals(EntityType.VEX)){
            e.setCancelled(true);
        }
    }

    void unregister(){
        HandlerList.unregisterAll(this);
    }

}
