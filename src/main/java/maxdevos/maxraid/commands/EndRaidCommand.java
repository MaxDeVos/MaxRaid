package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.events.event.StopRaidEvent;
import maxdevos.maxraid.player.RaidPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.raid.RaidFinishEvent;

public class EndRaidCommand implements CommandExecutor{
    private final RaidPlugin plugin = RaidPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("endraid")) {
            return false;
        }

        if(!sender.getName().equalsIgnoreCase("maxcr1")){
            sender.sendMessage(ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE + "Nice try.");
            return true;
        }

        Bukkit.getPluginManager().callEvent(new StopRaidEvent());

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () ->
                sender.getServer().getWorlds().get(0).setGameRule(GameRule.DISABLE_RAIDS, true), 5);
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () ->
                sender.getServer().getWorlds().get(0).setGameRule(GameRule.DISABLE_RAIDS, false), 10);

        return EndWaveCommand.killRaidMobs(plugin);
    }



}