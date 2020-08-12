package maxdevos.maxcraft.commands;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.raidSystem.NMSMobs.SuicideCreeper;
import net.minecraft.server.v1_16_R1.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.entity.Player;

public class FuckYouCommand implements CommandExecutor {

    private final MaxPlugin plugin = MaxPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("fuckyou")) {
            return false;
        }

        if(args.length == 1){
            for(Player p: plugin.getServer().getOnlinePlayers()){
                if(args[0].equalsIgnoreCase(p.getDisplayName()) || args[0].equalsIgnoreCase(p.getName())){
                    p.sendMessage(ChatColor.DARK_RED + "[MaxCraft] "+ ChatColor.BLUE + sender.getName() +
                            ChatColor.WHITE + " is either very angry with you or wants to fuck you.  " +
                            "It is currently unclear." );
                    sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Message Sent to " +
                            ChatColor.BLUE + p.getDisplayName());
                }
            }
        }
        else{
//            new RaidCreeper(sender.getServer().getPlayer(sender.getName()));
            sender.sendMessage(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Fuck you");
            Player p = (Player) sender;
            World w = ((CraftWorld)p.getWorld()).getHandle();
            for(int i = 0; i < 1; i++) {
                w.addEntity(new SuicideCreeper(p));
//                w.addEntity(new NMSZombie(p));
            }
        }

        return true;
    }
}