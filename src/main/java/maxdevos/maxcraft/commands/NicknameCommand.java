package maxdevos.maxcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NicknameCommand implements CommandExecutor {
    MaxPlugin plugin;

    public NicknameCommand(MaxPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("nickname")) {
            return false;
        }

        if(args.length != 2){
            sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "You did it wrong asshole");
            return false;
        }

        return true;
    }
}