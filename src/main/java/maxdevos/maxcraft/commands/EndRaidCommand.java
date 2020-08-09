package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.raidSystem.raidEvents.StopRaidEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static maxdevos.maxcraft.commands.EndWaveCommand.killRaidMobs;

public class EndRaidCommand implements CommandExecutor{
    private final MaxPlugin plugin = MaxPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("endraid")) {
            return false;
        }

        if(!sender.getName().equalsIgnoreCase("maxcr1")){
            sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Nice try.");
            return true;
        }

        Bukkit.getPluginManager().callEvent(new StopRaidEvent());
        return killRaidMobs(plugin);
    }



}