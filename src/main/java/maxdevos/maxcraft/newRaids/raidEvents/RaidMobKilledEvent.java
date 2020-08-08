package maxdevos.maxcraft.newRaids.raidEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;

public class RaidMobKilledEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    public final Player player;

    public RaidMobKilledEvent(EntityDeathEvent e){
        this.player = e.getEntity().getKiller();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
