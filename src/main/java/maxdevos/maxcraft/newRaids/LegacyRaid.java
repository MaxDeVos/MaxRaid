package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

@SuppressWarnings("unused")
class LegacyRaid implements Listener {

    private final Raid raid;
    private final World w;
    private final RaidEventHandler handler;

    public LegacyRaid(Raid raid){

        handler = new RaidEventHandler();
        this.raid = raid;
        MaxPlugin plugin = MaxPlugin.getInstance();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.w = plugin.getServer().getWorlds().get(0);
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.LIGHT_PURPLE + "YOU'RE IN MY TURF NOW MOTHERFUCKERS.");
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.LIGHT_PURPLE + "YOU'RE GONNA REGRET LEAVING LAST NIGHT");
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "This Raid is sponsored by RAID Shadow Legends");

    }

    @EventHandler
    private void newWave(RaidSpawnWaveEvent raidEvent){

        int wave = 0;
        System.out.println("Wave #: " + wave);
        raid.getHeroes();
    }


    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity ent = event.getEntity();

        if (ent instanceof Fireball) {
            event.setCancelled(true);
            w.createExplosion(ent.getLocation(), 2);
        }
    }

    @EventHandler
    private void endRaid(RaidFinishEvent e){
        System.out.println(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "The Raid is Over.");
        HandlerList.unregisterAll(this);
        handler.unregister();
    }
}
