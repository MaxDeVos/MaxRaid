package maxdevos.maxraid.player;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.ordinances.AWP;
import maxdevos.maxraid.ordinances.Excalibur;
import maxdevos.maxraid.ordinances.RaidOrdinance;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public class RaidPlayer implements Comparable<RaidPlayer> {

    private final int ordThresh = 20;
    private final UUID p;
    private int totalKills = 0;
    private int ordKills = 0;
    private static final RaidPlugin plugin = RaidPlugin.getInstance();

    public static void addNewPlayers(Set<UUID> ids, ArrayList<RaidPlayer> players){

        for(UUID u:ids){
            checkDuplicatePlayers(u, players);
        }

    }

    public static List<Player> getPlayerListFromUUIDs(Set<UUID> set){
        ArrayList<Player> out = new ArrayList<>();
        for(UUID uuid:set){
            out.add(RaidPlugin.getInstance().getServer().getPlayer(uuid));
        }
        return out;
    }

    public static boolean isPlayerPresent(UUID p, ArrayList<RaidPlayer> players){

        for(RaidPlayer rp : players){
            if (p.equals(rp.getPlayerID())) {
                return true;
            }
        }
        return false;

    }

    public static void addNewPlayers(UUID id, ArrayList<RaidPlayer> players){

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

    public static ArrayList<Player> getPlayersFromRaidPlayers(ArrayList<RaidPlayer> raidPlayers){
        ArrayList<Player> players = new ArrayList<>();
        for(RaidPlayer rP: raidPlayers){
            players.add(rP.getPlayer());
        }
        return players;
    }

    public static void checkDevMode(ArrayList<RaidPlayer> raidPlayers){
        if(plugin.getServer().getOnlinePlayers().size() == 1){
            try {
                raidPlayers.add(new RaidPlayer(Objects.requireNonNull(plugin.getServer().getPlayer("maxcr1"))));
                raidPlayers.get(0).getPlayer().getInventory().addItem(new Excalibur(1562).getItem(),
                        new AWP(384).getItem(), new ItemStack(Material.ARROW));
            }
            catch(Exception ignored){

            }
        }
    }

    private RaidPlayer(Player p){
        this.p = p.getUniqueId();
    }

    public static void removePlayer(UUID uniqueId, ArrayList<RaidPlayer> players) {
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

    public void addKill(){
        totalKills++;
        ordKills++;
    }

    public void handleOrdinance(){
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
//        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a(("{\"text\": \"" + message + "\"}"));
//        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, ChatMessageType.GAME_INFO, getPlayerID());
//        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(ppoc);
    }

    public void processScore(Scoreboard board){
        getPlayer().setScoreboard(board);
    }

    @Override
    public int compareTo(RaidPlayer o) {
        if(getKills() > o.getKills()){
            return 1;
        }
        else if(getKills() == o.getKills()){
            return 0;
        }
        return -1;
    }
}
