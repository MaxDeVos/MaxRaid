package maxdevos.maxcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MaxHelp implements CommandExecutor {
    MaxPlugin plugin;

    public MaxHelp(MaxPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();
        String problem = "";

        if (!cmdName.equals("maxhelp")) {
            return false;
        }

        for(String word:args){
            problem += word + " ";
        }

        sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Message sent straight to the recycling bin.  Fuck you.");

        return true;
    }
}