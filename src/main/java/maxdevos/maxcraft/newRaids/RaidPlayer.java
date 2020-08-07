package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class RaidPlayer {

    private Player p;

    static ArrayList<RaidPlayer> addNewPlayers(MaxPlugin plugin, Set<UUID> ids, ArrayList<RaidPlayer> players){

        for(UUID u:ids){
            boolean match = false;
            for(RaidPlayer rp : players){
                if(rp.getPlayer().getUniqueId().equals(u)){
                    match = true;
                    break;
                }
            }
            if(!match){
                players.add(new RaidPlayer(plugin.getServer().getPlayer(u)));
            }
        }
        return players;

    }

    static ArrayList<Player> getPlayersFromRaidPlayers(ArrayList<RaidPlayer> raidPlayers){
        ArrayList<Player> players = new ArrayList<>();
        for(RaidPlayer rP: raidPlayers){
            players.add(rP.getPlayer());
        }
        return players;
    }

    static ArrayList<RaidPlayer> checkDevMode(MaxPlugin plugin, ArrayList<RaidPlayer> raidPlayers){
        if(plugin.getServer().getOnlinePlayers().size() == 1){
            try {
                raidPlayers.add(new RaidPlayer(plugin.getServer().getPlayer("maxcr1")));
            }
            catch(Exception ignored){

            }
        }
        return raidPlayers;
    }

    RaidPlayer(Player p){
        this.p = p;
    }

    Player getPlayer(){
        return p;
    }

}
