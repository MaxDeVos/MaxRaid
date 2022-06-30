package maxdevos.maxraid.ordinances;

import maxdevos.maxraid.player.RaidPlayer;
import maxdevos.maxraid.util.ChatFunctions;

public class RaidOrdinance {

    public RaidOrdinance(RaidPlayer p) {
        p.getPlayer().sendMessage(ChatFunctions.raidPrefix + "Your Ordinance Has Spawned!");
        if(Math.random() > 0.5) {
            p.getPlayer().getInventory().addItem(new Excalibur(10).getItem());
        } else{
            p.getPlayer().getInventory().addItem(new AWP(10).getItem());
        }

    }

}
