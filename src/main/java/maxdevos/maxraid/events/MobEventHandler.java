package maxdevos.maxraid.events;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.Server;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
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

    public void unregister(){
        HandlerList.unregisterAll(this);
    }
}
