package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        if(args.length == 1){
            for(Player p: plugin.getServer().getOnlinePlayers()){
                if(args[0].equalsIgnoreCase(p.getDisplayName()) || args[0].equalsIgnoreCase(p.getName())){
                    p.sendMessage(ChatColor.DARK_RED + "[MaxCraft] "+ ChatColor.BLUE + sender.getName() +
                            ChatColor.WHITE + " is either very angry with you or wants to fuck you.  " +
                            "It is currently unclear." );
                    sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Message Sent to " +
                            ChatColor.BLUE + p.getDisplayName());
                }
            }
        }
        else{
            sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Fuck you too");
        }

        return true;
    }
}