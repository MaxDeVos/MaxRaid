package maxdevos.maxcraft.tedcruz;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TedCruzTwitterCommand implements CommandExecutor {
    MaxPlugin plugin;

    public TedCruzTwitterCommand(MaxPlugin plugin) {
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