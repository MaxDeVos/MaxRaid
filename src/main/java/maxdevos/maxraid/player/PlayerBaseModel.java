package maxdevos.maxraid.player;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.player.RaidPlayer;
import maxdevos.maxraid.util.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class PlayerBaseModel {

    private Server server = RaidPlugin.getServerInstance();
    private final ArrayList<Player> players;
    PlayerBaseModel(World w, ArrayList<RaidPlayer> raidPlayers){
        players = RaidPlayer.getPlayersFromRaidPlayers(raidPlayers);
        Location baseLocation = PlayerUtils.getAveragePlayerLocation(raidPlayers);
    }

    void updateModel(){
        server = RaidPlugin.getServerInstance();
    }

}
