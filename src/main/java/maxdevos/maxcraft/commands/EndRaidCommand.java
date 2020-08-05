package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class EndRaid implements CommandExecutor{
    MaxPlugin plugin;

    public EndRaid(MaxPlugin plugin) {
        this.plugin = plugin;
    }

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

        for(Entity e : plugin.getServer().getWorlds().get(0).getEntities()){
            if(e.getType().equals(EntityType.VEX) || e.getType().equals(EntityType.PILLAGER) ||
                    e.getType().equals(EntityType.RAVAGER) || e.getType().equals(EntityType.CREEPER) ||
                    e.getType().equals(EntityType.SPIDER) || e.getType().equals(EntityType.SKELETON) ||
                    e.getType().equals(EntityType.ZOMBIE) || e.getType().equals(EntityType.WITCH) ||
                    e.getType().equals(EntityType.ENDERMAN) || e.getType().equals(EntityType.GHAST) ||
                    e.getType().equals(EntityType.ILLUSIONER) || e.getType().equals(EntityType.EVOKER) ||
                    e.getType().equals(EntityType.VINDICATOR)){
                e.remove();
            }
        }

        return false;
    }



}