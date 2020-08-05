package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaxInvisible implements CommandExecutor {
    MaxPlugin plugin;

    public MaxInvisible(MaxPlugin plugin) {
        this.plugin = plugin;
    }

    boolean hidden = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("maxv") || !sender.getName().equals("maxcr1")) {
            return false;
        }

        if (!hidden){
            Player p = plugin.getServer().getPlayer("maxcr1");
            for (Player players : plugin.getServer().getOnlinePlayers()) {
                players.hidePlayer(p);
            }
            hidden = true;
        } else{
            Player p = plugin.getServer().getPlayer("maxcr1");
            for (Player players : plugin.getServer().getOnlinePlayers()) {
                players.showPlayer(p);
            }
        }
            hidden = false;

        return true;
    }
}