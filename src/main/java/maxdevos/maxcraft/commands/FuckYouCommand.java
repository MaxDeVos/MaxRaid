package maxdevos.maxcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FuckYouCommand implements CommandExecutor {
    MaxPlugin plugin;

    public FuckYouCommand(MaxPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("fuckyou")) {
            return false;
        }
        sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Fuck you too.");
        return true;
    }
}