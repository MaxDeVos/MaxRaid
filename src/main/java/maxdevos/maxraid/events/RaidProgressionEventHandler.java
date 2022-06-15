package maxdevos.maxraid.events;

import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class RaidProgressionEventHandler implements Listener {

    MaxRaid raid;

    public RaidProgressionEventHandler(MaxRaid raid){
        this.raid = raid;
    }

    public void unregister(){
        HandlerList.unregisterAll(this);
    }
}
