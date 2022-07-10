package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PosCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
		if (!(sender instanceof Player) || !arg1.getName().equalsIgnoreCase("pos")) {
			return false;
		}
		Player p = (Player) sender;
		if(args.length == 0){
			RaidPlugin.savedLoc = p.getLocation();
			RaidPlugin.getInstance().getServer().broadcastMessage(ChatColor.GREEN + "Set Location at " + RaidPlugin.savedLoc);
			return false;
		}
		return false;
	}
}
