package maxdevos.maxcraft.tedcruz;

import maxdevos.maxcraft.MaxCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TedCruzTwitterCommand implements CommandExecutor {
    MaxCraft plugin;

    public TedCruzTwitterCommand(MaxCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("fuckyou")) {
            return false;
        }

        sender.sendMessage("Fuck you too.");

        return true;
    }
}