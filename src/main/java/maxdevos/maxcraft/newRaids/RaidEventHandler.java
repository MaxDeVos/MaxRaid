package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.newRaids.newRaidMods.RaidPillager;
import maxdevos.maxcraft.newRaids.newRaidMods.RaidVindicator;
import maxdevos.maxcraft.newRaids.raidEvents.RaidMobKilledEvent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import java.util.Objects;

class RaidEventHandler implements Listener {

    private final MaxPlugin plugin = MaxPlugin.getInstance();

    RaidEventHandler(){
        Server server = MaxPlugin.getServerInstance();
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
        if (!(e instanceof Player) && !(e.getDamager() instanceof Player)) {
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

    @EventHandler
    private void raidMobKilled(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null && e.getEntity().getCustomName() != null){
            Bukkit.getPluginManager().callEvent(new RaidMobKilledEvent(e));
        }
    }

    @EventHandler
    private void killedMob(EntitySpawnEvent e){
        if(e.getEntity().getCustomName() == null){

        }
    }

    void unregister(){
        HandlerList.unregisterAll(this);
    }

}
