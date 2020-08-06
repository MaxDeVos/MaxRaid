package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.util.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import java.util.ArrayList;

public class ConfigBasedRaid implements Listener {

    private final MaxPlugin plugin;
    private final Raid raid;
    private final World w;
    private int wave = 1;
    ArrayList<Player> players = new ArrayList<>();
    private RaidEventHandler handler;
    private RaidConfig raidConfig;

    public ConfigBasedRaid(MaxPlugin plugin, Raid raid){

        raidConfig = new RaidConfig(plugin, plugin.getCustomConfig().getString("current-raid"));
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
    private void newWave(RaidSpawnWaveEvent e){

        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "Wave # "
                + wave + " has spawned!");
        players = PlayerUtils.getPlayersFromUUIDs(plugin, raid.getHeroes());

        if(wave != 1) {
            RaidWave currentWave = raidConfig.getWave(wave);
            currentWave.configWave(players, e);
            currentWave.spawnWave();
            currentWave.spawnAirdrops();
        }
        wave++;
    }

    @EventHandler
    private void endRaid(RaidFinishEvent e){
        System.out.println(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "The Raid is Over.");
        HandlerList.unregisterAll(this);
        handler.unregister();
    }
}
