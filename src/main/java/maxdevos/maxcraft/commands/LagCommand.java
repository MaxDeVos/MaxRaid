package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.util.TPSCalc;
import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LagCommand implements CommandExecutor {
    MaxPlugin plugin;

    public LagCommand(MaxPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("lag")) {
            return false;
        }

        sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + TPSCalc.getTPS());

        return true;
    }
}