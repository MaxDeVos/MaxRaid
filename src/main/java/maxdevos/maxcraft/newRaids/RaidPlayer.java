package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import net.minecraft.server.v1_16_R1.ChatMessageType;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class RaidPlayer {

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

    void addKill(){
        killedMobs++;
    }

    @SuppressWarnings("unused")
    int getKills(){
        return killedMobs;
    }

    void setInfoText(String message){
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a(("{\"text\": \"" + message + "\"}"));
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, ChatMessageType.GAME_INFO, getPlayerID());
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(ppoc);
    }

}
