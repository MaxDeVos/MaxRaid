package maxdevos.maxcraft.raidSystem;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.util.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class PlayerBaseModel {

    private Server server = MaxPlugin.getServerInstance();
    private ArrayList<Player> players;
    PlayerBaseModel(World w, ArrayList<RaidPlayer> raidPlayers){
        players = RaidPlayer.getPlayersFromRaidPlayers(raidPlayers);
        Location baseLocation = PlayerUtils.getAveragePlayerLocation(raidPlayers);
    }

    void updateModel(){
        server = MaxPlugin.getServerInstance();
    }

}
