package maxdevos.maxraid.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String cmdName = command.getName().toLowerCase();

        if(!cmdName.equals("reset") && !cmdName.equals("r")){
            return false;
        }

        Bukkit.dispatchCommand(commandSender, "endraid");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"kill @e[type=!minecraft:villager,type=!minecraft:player]");

        if(cmdName.equals("reset")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/world world");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/pos1 -545,40,460");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/pos2 -397,142,648");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/restore");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"kill @e[type=!minecraft:villager,type=!minecraft:player]");
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"reload confirm");

        return true;

    }
}
