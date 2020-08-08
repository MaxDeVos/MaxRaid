package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MaxInvisible implements CommandExecutor {

    private final MaxPlugin plugin = MaxPlugin.getInstance();

    private boolean hidden = false;

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("maxv") || !sender.getName().equals("maxcr1")) {
            return false;
        }

        if (!hidden){
            Player p = plugin.getServer().getPlayer("maxcr1");
            for (Player players : plugin.getServer().getOnlinePlayers()) {
                players.hidePlayer(Objects.requireNonNull(p));
            }
            hidden = true;
        } else{
            Player p = plugin.getServer().getPlayer("maxcr1");
            for (Player players : plugin.getServer().getOnlinePlayers()) {
                players.showPlayer(Objects.requireNonNull(p));
            }
        }
            hidden = false;

        return true;
    }
}