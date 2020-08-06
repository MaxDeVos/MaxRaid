package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.newRaids.newRaidMods.*;
import maxdevos.maxcraft.util.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import java.util.ArrayList;
import java.util.UUID;

public class LegacyRaid implements Listener {

    private final MaxPlugin plugin;
    private final Raid raid;
    private final World w;
    private int wave = 0;
    ArrayList<Player> players = new ArrayList<>();
    RaidEventHandler handler;

    public LegacyRaid(MaxPlugin plugin, Raid raid){

        handler = new RaidEventHandler(plugin);
        this.plugin = plugin;
        this.raid = raid;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.w = plugin.getServer().getWorlds().get(0);
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.LIGHT_PURPLE + "YOU'RE IN MY TURF NOW MOTHERFUCKERS.");
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.LIGHT_PURPLE + "YOU'RE GONNA REGRET LEAVING LAST NIGHT");
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "This Raid is sponsored by RAID Shadow Legends");

    }

    @EventHandler
    private void newWave(RaidSpawnWaveEvent raidEvent){

        System.out.println("Wave #: " + wave);
        raid.getHeroes();
        ArrayList<Player> players = new ArrayList<>();
        for (UUID d:raid.getHeroes()) {
        }
    }

    private void spawnAirDrop(Player p){

        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "AIRDROP INCOMING ON "
                + p.getDisplayName());
        Location l = p.getLocation().add(0,15,0);

        int tempWave = Math.min(wave, 4);

        for(int i = 2; i < tempWave; i++){
        }

    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity ent = event.getEntity();

        if (ent instanceof Fireball) {
            event.setCancelled(true);
            if (wave != 7) {
                w.createExplosion(ent.getLocation(), 2);
            } else {
                w.createExplosion(ent.getLocation(), 7);
            }
        }
    }

    @EventHandler
    private void endRaid(RaidFinishEvent e){
        System.out.println(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "The Raid is Over.");
        HandlerList.unregisterAll(this);
        handler.unregister();
    }
}
