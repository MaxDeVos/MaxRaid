package maxdevos.maxraid.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;

@SuppressWarnings("unused")
public class ChatFunctions {

    public static final String raidPrefix = ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE;
    public static String prefix = ChatColor.DARK_RED + "[MaxRaid] " + ChatColor.WHITE;

    public static boolean isRaider(Entity e){
        if(e.getCustomName() != null){
            return e.getCustomName().contains("Raid");
        }
        return false;
    }

}
