package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

class RaidPlayer {

    private final UUID p;
    private int killedMobs = 0;
    private static final MaxPlugin plugin = MaxPlugin.getInstance();

    static void addNewPlayers(Set<UUID> ids, ArrayList<RaidPlayer> players){

        for(UUID u:ids){
            boolean match = false;
            for(RaidPlayer rp : players){
                if(rp.getPlayerID().equals(u)){
                    match = true;
                    break;
                }
            }
            if(!match){
                players.add(new RaidPlayer(Objects.requireNonNull(plugin.getServer().getPlayer(u))));
            }
        }

    }

    static ArrayList<Player> getPlayersFromRaidPlayers(ArrayList<RaidPlayer> raidPlayers){
        ArrayList<Player> players = new ArrayList<>();
        for(RaidPlayer rP: raidPlayers){
            players.add(rP.getPlayer());
        }
        return players;
    }

    static void checkDevMode(ArrayList<RaidPlayer> raidPlayers){
        if(plugin.getServer().getOnlinePlayers().size() == 1){
            try {
                raidPlayers.add(new RaidPlayer(Objects.requireNonNull(plugin.getServer().getPlayer("maxcr1"))));
            }
            catch(Exception ignored){

            }
        }
    }

    private RaidPlayer(Player p){
        this.p = p.getUniqueId();
    }

    public Player getPlayer(){
        return plugin.getServer().getPlayer(p);
    }

    private UUID getPlayerID(){
        return p;
    }

    public void addKill(){
        killedMobs++;
    }

    @SuppressWarnings("unused")
    public int getKills(){
        return killedMobs;
    }

}
