package maxdevos.maxraid.config;

import maxdevos.maxraid.RaidPlugin;
import org.bukkit.Server;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

class RaidEventHandler implements Listener {

    private final RaidPlugin plugin = RaidPlugin.getInstance();

    RaidEventHandler(){
        Server server = RaidPlugin.getServerInstance();
        server.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void friendlyFire(EntityTargetEvent e) {
        if (!(e instanceof Player) && !(e.getTarget() instanceof Player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void friendlyFire(EntityDamageByEntityEvent e) {
        if(!(e instanceof Player)){
            if(e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)){
                Projectile shot = (Projectile) e.getDamager();
                if(!(shot.getShooter() instanceof Player)){
                    e.setCancelled(true);
                }
            }
            else if (!(e.getDamager() instanceof Player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void antiExplosion(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setCancelled(true);
            }
        }
        if((e.getEntity() instanceof Player)) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setDamage(e.getDamage() * .30);
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

    void unregister(){
        HandlerList.unregisterAll(this);
    }

}
