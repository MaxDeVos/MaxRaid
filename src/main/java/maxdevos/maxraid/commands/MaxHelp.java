package maxdevos.maxraid.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
public class MaxHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();
        StringBuilder problem = new StringBuilder();

        if (!cmdName.equals("maxhelp")) {
            return false;
        }

        for(String word:args){
            problem.append(word).append(" ");
        }

        sender.sendMessage(ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE + "Message sent straight to the recycling bin.  Fuck you.");

        return true;
    }
}