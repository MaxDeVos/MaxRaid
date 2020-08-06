package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.commands.EndRaidCommand;
import maxdevos.maxcraft.util.PlayerUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.event.raid.RaidStopEvent;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class ConfigBasedRaid implements Listener {

    private final MaxPlugin plugin;
    private final Raid raid;
    private final World w;
    private int wave = 1;
    ArrayList<RaidPlayer> players = new ArrayList<>();
    private final RaidEventHandler handler;
    private final RaidConfig raidConfig;
    private RaidWave currentWave;

    public ConfigBasedRaid(MaxPlugin plugin, Raid raid){

        raidConfig = new RaidConfig(plugin, plugin.getCustomConfig().getString("current-raid"));
        handler = new RaidEventHandler(plugin);
        this.plugin = plugin;
        this.raid = raid;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.w = plugin.getServer().getWorlds().get(0);
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.LIGHT_PURPLE + "Running Raid: " + raidConfig.getRaidName());
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "This Raid is sponsored by RAID Shadow Legends");
        currentWave = new RaidWave();

    }

    @EventHandler
    private void newWave(RaidSpawnWaveEvent e){

        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "Wave # "
                + wave + " has spawned!");
        players = RaidPlayer.addNewPlayers(plugin,raid.getHeroes(),players);

        for(RaidPlayer p:players){
            System.out.println(p.getPlayer().getCustomName());
        }

        if(wave != 1) {
            currentWave = raidConfig.getWave(wave);
            currentWave.configWave(players, e);
            currentWave.spawnWave();
            currentWave.spawnAirdrops();
        }
        wave++;
    }

    @EventHandler
    private void endRaid(RaidFinishEvent e){
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "The Raid is Over.");
        HandlerList.unregisterAll(this);
        handler.unregister();
    }

    @EventHandler
    private void endRaid(StopRaidEvent e){
        w.setGameRule(GameRule.DISABLE_RAIDS, true);
        currentWave.killAll();
        Bukkit.getPluginManager().callEvent(new RaidFinishEvent(raid, w, RaidPlayer.getPlayersFromRaidPlayers(players)));
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                w.setGameRule(GameRule.DISABLE_RAIDS, false);
            }
        }, 2L);
    }

    @EventHandler
    private void killWave(KillWaveEvent e){
        currentWave.killAll();
    }

}
