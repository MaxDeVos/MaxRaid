package maxdevos.maxcraft;

import maxdevos.maxcraft.newRaids.ConfigBasedRaid;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.raid.RaidTriggerEvent;

import java.util.Random;

@SuppressWarnings("unused")
final class CatchAllListener implements Listener {

    private final MaxPlugin plugin = MaxPlugin.getInstance();

    CatchAllListener() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        Random rand = new Random();
    }

    @EventHandler
    public void normalQuit(PlayerQuitEvent event) {
        String username = event.getPlayer().getName().toLowerCase();
        String nickname = plugin.getCustomConfig().getString("nicknames."+username);
        String quip = plugin.getCustomConfig().getString("messages.quit");
        if(nickname != null) {
            event.setQuitMessage(ChatColor.YELLOW + nickname + " " + quip);
        }
        else{
            event.setQuitMessage(ChatColor.YELLOW + username + " " + quip);
        }
    }

    @EventHandler
    public void normalLogin(PlayerJoinEvent event) {
        String username = event.getPlayer().getName().toLowerCase();
        String nickname = plugin.getCustomConfig().getString("nicknames."+username);
        String quip = plugin.getCustomConfig().getString("messages.join");
        if(nickname != null) {
            event.setJoinMessage(ChatColor.YELLOW + nickname + " " + quip);
            event.getPlayer().setDisplayName(nickname);
            event.getPlayer().setPlayerListName(nickname);
        }
        else{
            event.setJoinMessage(ChatColor.YELLOW + username + " " + quip);
        }
    }

    @EventHandler
    private void antiGolem(EntityTargetLivingEntityEvent e){
        if(e.getEntityType().equals(EntityType.IRON_GOLEM)) {
            try{
                if(e.getTarget() instanceof Player){
                    e.setCancelled(true);
                }
            }
            catch (Exception ignored){
            }
        }
    }

    @EventHandler
    private void raidTime(RaidTriggerEvent e){
        new ConfigBasedRaid(e.getRaid());
    }
}