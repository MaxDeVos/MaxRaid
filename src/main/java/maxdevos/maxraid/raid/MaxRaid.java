package maxdevos.maxraid.raid;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.RaidScoreboard;
import maxdevos.maxraid.events.MobEventHandler;
import maxdevos.maxraid.events.RaidProgressionEventHandler;
import maxdevos.maxraid.player.RaidPlayer;
import maxdevos.maxraid.events.event.KillWaveEvent;
import maxdevos.maxraid.events.event.RaidMobKilledEvent;
import maxdevos.maxraid.events.event.StopRaidEvent;
import maxdevos.maxraid.util.ChatFunctions;
import maxdevos.maxraid.util.PlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMonster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.util.BlockVector;

import java.util.ArrayList;

public class MaxRaid implements Listener {

    private final RaidPlugin plugin = RaidPlugin.getInstance();
    private final NMSRaid nmsRaid;
    private final Raid bukkitRaid;
    private final World world;
    public final RaidBase raidBase;
    private int wave = 0;
    private final ArrayList<RaidPlayer> players = new ArrayList<>();
    private RaidProgressionEventHandler progressionEventHandler;
    private MobEventHandler mobEventHandler;
    private final RaidConfig raidConfig;
    private final RaidWave currentWave;
    private final RaidScoreboard scoreboard;

    public MaxRaid(NMSRaid nmsRaid, Raid bukkitRaid){
        this.nmsRaid = nmsRaid;
        this.bukkitRaid = bukkitRaid;
        world = nmsRaid.bukkitWorld;
        raidBase = new RaidBase(nmsRaid.serverLevel, new BlockVector(-518, 140, 557), new BlockVector(-433, 80, 611));

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


    public void addMob(CraftMonster mob){
        this.nmsRaid.addMob(mob.getHandle());
    }

    public BlockVector getVillageCenter(){
        return raidBase.center;
    }

    @EventHandler
    private void endRaid(RaidFinishEvent e){
        plugin.getServer().broadcastMessage(ChatFunctions.raidPrefix + "The Raid is Over.");

        PlayerUtils.printKillOrder(players);
        scoreboard.restoreScoreboard();

        HandlerList.unregisterAll(this);
        nmsRaid.unregister();
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
        for(LivingEntity entity:nmsRaid.raidMobs){
            if(entity.isAlive()){
                Bukkit.getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> entity.kill(), 1);
            }
        }
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
        nmsRaid.updateBossbar();

        if(nmsRaid.getTotalRaidersAlive() < 5){
            for(LivingEntity mob: nmsRaid.raidMobs){
                mob.setGlowingTag(true);
            }
        }

    }

    @EventHandler
    private void raidMobKilled(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null && nmsRaid.isUUIDRaider(e.getEntity().getUniqueId())){
            Bukkit.getPluginManager().callEvent(new RaidMobKilledEvent(e));
        }
    }

    @EventHandler
    private void handleEntityDamaged(EntityDamageEvent e){
        if(nmsRaid.isUUIDRaider(e.getEntity().getUniqueId())){
            nmsRaid.updateBossbar();
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
