package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class NicknameCommand implements CommandExecutor{

    private final RaidPlugin plugin = RaidPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("nickname")) {
            return false;
        }

        if(args.length == 1){
            setNickname(sender, sender.getName(), args[0]);
            return true;
        }

        if(args.length == 2){
            System.out.println(sender.getName());
            if(!sender.isOp() || !(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE + "I'm not letting you set other " +
                        "people's nicknames.  Type in your name and we'll call it a day.");
                return true;
            }
            try{
                setNickname(sender, args[0], args[1]);
            }
            catch (Exception e){
                e.printStackTrace();
                sender.sendMessage(ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE + "something went horribly wrong");
            }
            return true;
        }

        return false;
    }

    private void setNickname(CommandSender sender, String username, String nickname){
        String effectedUser;
        String apos;
        username = username.toLowerCase();

        if(sender.getName().equalsIgnoreCase(username)){
             effectedUser = ChatColor.WHITE + "your";
             apos = "";
        }
        else{
            effectedUser = ChatColor.BLUE + username;
            apos = "'s";
        }

        String tempNickname;
        if(plugin.getCustomConfig().get("nicknames."+username) == null){
            tempNickname = "";
        }
        else{
            tempNickname = plugin.getCustomConfig().getString("nicknames."+username);
        }

        if(nickname.equals(tempNickname)){
            sender.sendMessage(ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE + " " + effectedUser
                    + ChatColor.WHITE + apos + " nickname is already " + ChatColor.BLUE + nickname);
            return;
        }

        if(plugin.getCustomConfig().getString("nicknames."+username) == null){
            sender.sendMessage(ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE + "Set " +
                    effectedUser + ChatColor.WHITE + apos + " nickname to " + ChatColor.BLUE + nickname);
        }
        else{
            sender.sendMessage(ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE + "Changed " + effectedUser
                    + ChatColor.WHITE + apos + " nickname from " + ChatColor.BLUE +
                    plugin.getCustomConfig().get("nicknames."+username) + ChatColor.WHITE +
                    " to " + ChatColor.BLUE + nickname);
        }
        plugin.getCustomConfig().set("nicknames."+username, nickname);

        try{
            Objects.requireNonNull(Objects.requireNonNull(plugin.getServer().getPlayer(username)).getPlayer()).setDisplayName(nickname);
            Objects.requireNonNull(Objects.requireNonNull(plugin.getServer().getPlayer(username)).getPlayer()).setPlayerListName(nickname);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            plugin.getCustomConfig().save(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}