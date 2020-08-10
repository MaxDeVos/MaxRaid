package maxdevos.maxcraft.raidSystem;

import com.mysql.fabric.xmlrpc.base.Array;
import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.raidSystem.ordinances.AK47;
import maxdevos.maxcraft.raidSystem.ordinances.Excalibur;
import maxdevos.maxcraft.raidSystem.ordinances.RaidOrdinance;
import net.minecraft.server.v1_16_R1.ChatMessageType;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutChat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RaidPlayer implements Comparable<RaidPlayer> {

    private final int ordThresh = 20;
    private final UUID p;
    private int totalKills = 0;
    private int ordKills = 0;
    private static final MaxPlugin plugin = MaxPlugin.getInstance();

    static void addNewPlayers(Set<UUID> ids, ArrayList<RaidPlayer> players){

        for(UUID u:ids){
            checkDuplicatePlayers(u, players);
        }

    }

    static boolean isPlayerPresent(UUID p, ArrayList<RaidPlayer> players){

        for(RaidPlayer rp : players){
            if (p.equals(rp.getPlayerID())) {
                return true;
            }
        }
        return false;

    }

    static void addNewPlayers(UUID id, ArrayList<RaidPlayer> players){

        checkDuplicatePlayers(id, players);

    }

    private static void checkDuplicatePlayers(UUID id, ArrayList<RaidPlayer> players) {
        boolean match = false;
        for(RaidPlayer rp : players){
            if(rp.getPlayerID().equals(id)){
                match = true;
                break;
            }
        }
        if(!match){
            players.add(new RaidPlayer(Objects.requireNonNull(plugin.getServer().getPlayer(id))));
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
                raidPlayers.get(0).getPlayer().getInventory().addItem(new Excalibur(1562).getItem(),
                        new AK47(384).getItem(), new ItemStack(Material.ARROW));
            }
            catch(Exception ignored){

            }
        }
    }

    private RaidPlayer(Player p){
        this.p = p.getUniqueId();
    }

    static void removePlayer(UUID uniqueId, ArrayList<RaidPlayer> players) {
        for(RaidPlayer p:players){
            if(uniqueId.equals(p.getPlayerID())){
                players.remove(p);
            }
        }
    }

    public Player getPlayer(){
        return plugin.getServer().getPlayer(p);
    }

    private UUID getPlayerID(){
        return p;
    }

    void addKill(){
        totalKills++;
        ordKills++;
    }

    void handleOrdinance(){
        if(getNeededOrdKills() > 1){
            setInfoText("§bYou Are§f " + getNeededOrdKills() + " §bKills Away From Ordinance | Total Kills:§f " + getKills());
        }
        else if(getNeededOrdKills() == 1){
            setInfoText("§bYou Are§f " + getNeededOrdKills() + " §bKill Away From Ordinance | Total Kills:§f " + getKills());
        }
        else{
            setInfoText("§aOrdinance Spawned! | Total Kills:§f " + getKills());
            ordKills = 0;
            new RaidOrdinance(this);
        }
    }

    public int getKills(){
        return totalKills;
    }

    private int getNeededOrdKills(){
        return ordThresh - ordKills;
    }

    private void setInfoText(String message){
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a(("{\"text\": \"" + message + "\"}"));
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, ChatMessageType.GAME_INFO, getPlayerID());
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(ppoc);
    }

    public void processScore(Scoreboard board){
        getPlayer().setScoreboard(board);
    }

    @Override
    public int compareTo(@NotNull RaidPlayer o) {
        if(getKills() > o.getKills()){
            return 1;
        }
        else if(getKills() == o.getKills()){
            return 0;
        }
        return -1;
    }
}
