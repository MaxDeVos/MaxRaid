package maxdevos.maxraid.raid;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.RaidScoreboard;
import maxdevos.maxraid.events.MobEventHandler;
import maxdevos.maxraid.events.RaidProgressionEventHandler;
import maxdevos.maxraid.mobs.baseLegacy.*;
import maxdevos.maxraid.player.RaidPlayer;
import maxdevos.maxraid.events.event.KillWaveEvent;
import maxdevos.maxraid.events.event.RaidMobKilledEvent;
import maxdevos.maxraid.events.event.StopRaidEvent;
import maxdevos.maxraid.util.ChatFunctions;
import maxdevos.maxraid.util.PlayerUtils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import java.util.ArrayList;

public class MaxRaid implements Listener {

    private final RaidPlugin plugin = RaidPlugin.getInstance();
    private final NMSRaid nmsRaid;
    private final Raid bukkitRaid;
    private final World world;
    private int wave = 0;
    private final ArrayList<RaidPlayer> players = new ArrayList<>();
    private final RaidProgressionEventHandler progressionEventHandler;
    private final MobEventHandler mobEventHandler;
    private final RaidConfig raidConfig;
    private final RaidWave currentWave;
    private final RaidScoreboard scoreboard;

    public MaxRaid(NMSRaid nmsRaid, Raid bukkitRaid){
        this.nmsRaid = nmsRaid;
        this.world = nmsRaid.bukkitWorld;
        this.bukkitRaid = bukkitRaid;

        scoreboard = new RaidScoreboard();
        raidConfig = new RaidConfig(plugin.getCustomConfig().getString("current-raid"));

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().broadcastMessage(ChatFunctions.raidPrefix + "Running Raid: " + raidConfig.getRaidName());
        plugin.getServer().broadcastMessage(ChatFunctions.raidPrefix + "This Raid is sponsored by RAID Shadow Legends");
        currentWave = new RaidWave();
        RaidPlayer.checkDevMode(players);

        progressionEventHandler = new RaidProgressionEventHandler(this);
        mobEventHandler = new MobEventHandler(this);
    }

    public NMSRaid getHandle(){
        return nmsRaid;
    }


    @EventHandler
    private void newWave(RaidSpawnWaveEvent e) {

    }

    @EventHandler
    private void endRaid(RaidFinishEvent e){
        plugin.getServer().broadcastMessage(ChatFunctions.raidPrefix + "The Raid is Over.");

        PlayerUtils.printKillOrder(players);
        scoreboard.restoreScoreboard();

        HandlerList.unregisterAll(this);
        progressionEventHandler.unregister();
        mobEventHandler.unregister();

    }

    @EventHandler
    private void endRaid(StopRaidEvent e){
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        currentWave.killAll();
        Bukkit.getPluginManager().callEvent(new RaidFinishEvent(this.bukkitRaid, world, RaidPlayer.getPlayersFromRaidPlayers(players)));
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () ->
                world.setGameRule(GameRule.DISABLE_RAIDS, false), 2L);
    }

    @EventHandler
    private void killWave(KillWaveEvent e){
        currentWave.killAll();
    }

    @EventHandler
    private void killedMob(RaidMobKilledEvent e){
        if(wave == 1){
            RaidPlayer.addNewPlayers(e.player.getUniqueId(), players);
        }
        for(RaidPlayer p:players){
            if(p.getPlayer().getUniqueId().equals(e.player.getUniqueId())){
                p.addKill();
            }
            p.handleOrdinance();
        }
        scoreboard.updateScoreboard(players);
    }

    @EventHandler
    private void raidMobKilled(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null && nmsRaid.isUUIDRaider(e.getEntity().getUniqueId())){
            Bukkit.getPluginManager().callEvent(new RaidMobKilledEvent(e));
        }
    }

    @EventHandler
    public void normalQuit(PlayerQuitEvent event) {
        if(RaidPlayer.isPlayerPresent(event.getPlayer().getUniqueId(), players)){
            RaidPlugin.getServerInstance().broadcastMessage(ChatFunctions.raidPrefix +
                    event.getPlayer().getDisplayName() + " has left the action to avoid danger.  Their " +
                    "ordinance progress and kills have been reset.");
            RaidPlayer.removePlayer(event.getPlayer().getUniqueId(), players);
        }
    }
}
