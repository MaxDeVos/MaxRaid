package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;

import static org.bukkit.ChatColor.*;

public class SetRaidCommand implements CommandExecutor {

    private final MaxPlugin plugin = MaxPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("setraid")) {
            return false;
        }

        if(args.length != 1){
            return false;
        }

        File raidSetupFile = new File(plugin.getDataFolder(), args[0] + ".yml");
        if (!raidSetupFile.exists()) {
            sender.sendMessage(DARK_RED+ "[RAID ERROR] " + WHITE + "Raid Config File: " + GRAY +
                    args[0] + WHITE + " not found.  Fuck you.");
            return true;
        }

        plugin.getCustomConfig().set("current-raid",args[0]);
        try {
            plugin.getCustomConfig().save(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sender.sendMessage(DARK_RED+ "[MaxRaid] " + WHITE + "Successfully loaded " +
                args[0] + " raid file.");

        return true;
    }
}