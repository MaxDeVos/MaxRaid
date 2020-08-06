package maxdevos.maxcraft.util;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class PlayerUtils {

    public static Player getRandomPlayer(MaxPlugin plugin, Set<UUID> uuids){
        Random r = new Random();
        ArrayList<Player> out = new ArrayList<>();
        for(UUID p:uuids){
            out.add(plugin.getServer().getPlayer(p));
        }
        int p = r.nextInt(out.size()-1);
        return out.get(p);
    }

    public static Player getRandomPlayer(ArrayList<Player> players) {
        Random r = new Random();
        if (players.size() > 1) {
            int p = r.nextInt(players.size() - 1);
            return players.get(p);
        }
        return players.get(0);
    }

    public static ArrayList<Player> getPlayersFromUUIDs(MaxPlugin plugin, Set<UUID> ids){
        ArrayList<Player> players = new ArrayList<>();
        if(plugin.getServer().getOnlinePlayers().size() == 1){
            try {
                Player max = plugin.getServer().getPlayer("maxcr1");
                players.add(max);
                max.sendMessage("Debug Mode.  Added you as target.");
            }
            catch(Exception ignored){

            }
        }

        for(UUID id:ids){
            players.add(plugin.getServer().getPlayer(id));
        }
        return players;
    }

    public static Player getHighestPlayer(ArrayList<Player> players){
        Player out = players.get(0);
        for(Player p:players){
            if(p.getLocation().getBlockY() > out.getLocation().getBlockY()){
                out = p;
            }
        }
        return out;
    }

}

