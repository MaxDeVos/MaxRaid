package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class RaidPlayer {

    private UUID p;
    private int killedMobs = 0;

    static ArrayList<RaidPlayer> addNewPlayers(MaxPlugin plugin, Set<UUID> ids, ArrayList<RaidPlayer> players){

        for(UUID u:ids){
            boolean match = false;
            for(RaidPlayer rp : players){
                if(rp.getPlayerID().equals(u)){
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

    static ArrayList<Player> getPlayersFromRaidPlayers(MaxPlugin plugin, ArrayList<RaidPlayer> raidPlayers){
        ArrayList<Player> players = new ArrayList<>();
        for(RaidPlayer rP: raidPlayers){
            players.add(rP.getPlayer(plugin));
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

    public RaidPlayer(Player p){
        this.p = p.getUniqueId();
    }

    public Player getPlayer(MaxPlugin plugin){
        return plugin.getServer().getPlayer(p);
    }

    public UUID getPlayerID(){
        return p;
    }

    public void addKill(){
        killedMobs++;
    }

    public int getKills(){
        return killedMobs;
    }

}
