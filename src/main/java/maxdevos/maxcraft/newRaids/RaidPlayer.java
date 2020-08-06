package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class RaidPlayer {

    private Player p;

    static ArrayList<RaidPlayer> addNewPlayers(MaxPlugin plugin, Set<UUID> ids, ArrayList<RaidPlayer> players){

        if(plugin.getServer().getOnlinePlayers().size() == 1){
            try {
                Player max = plugin.getServer().getPlayer("maxcr1");
                players.add(new RaidPlayer(max));
                max.sendMessage("Debug Mode.  Added you as target.");
            }
            catch(Exception ignored){

            }
        }

        for(UUID u:ids){
            Player p = plugin.getServer().getPlayer(u);
            if(!players.contains(p)){
                players.add(new RaidPlayer(p));
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

    RaidPlayer(Player p){
        this.p = p;
    }

    Player getPlayer(){
        return p;
    }

}
