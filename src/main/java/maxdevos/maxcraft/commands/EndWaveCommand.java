package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.raidSystem.raidEvents.KillWaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EndWaveCommand implements CommandExecutor{
    private final MaxPlugin plugin = MaxPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("endwave")) {
            return false;
        }

        if(!sender.getName().equalsIgnoreCase("maxcr1")){
            sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Nice try.");
            return true;
        }

        Bukkit.getPluginManager().callEvent(new KillWaveEvent());
        return killRaidMobs(plugin);
    }

    @SuppressWarnings("SameReturnValue")
    static boolean killRaidMobs(MaxPlugin plugin) {
        for(Entity e : plugin.getServer().getWorlds().get(0).getEntities()){
            if(e.getType().equals(EntityType.VEX) || e.getType().equals(EntityType.PILLAGER) ||
                    e.getType().equals(EntityType.RAVAGER) || e.getType().equals(EntityType.WITCH) ||
                    e.getType().equals(EntityType.ENDERMAN) || e.getType().equals(EntityType.GHAST) ||
                    e.getType().equals(EntityType.ILLUSIONER) || e.getType().equals(EntityType.EVOKER) ||
                    e.getType().equals(EntityType.VINDICATOR)){
                e.remove();
            }
        }

        return true;
    }


}