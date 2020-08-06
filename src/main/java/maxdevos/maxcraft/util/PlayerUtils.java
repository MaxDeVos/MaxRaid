package maxdevos.maxcraft.util;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
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

    public static ArrayList<Player> getPlayersFromUUIDs(MaxPlugin p, Set<UUID> ids){
        ArrayList<Player> players = new ArrayList<>();
        for(UUID id:ids){
            players.add(p.getServer().getPlayer(id));
        }
        return players;
    }
}

