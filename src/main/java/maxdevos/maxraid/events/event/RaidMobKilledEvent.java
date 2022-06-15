package maxdevos.maxraid.events.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;

public class RaidMobKilledEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    public final Player player;

    public RaidMobKilledEvent(EntityDeathEvent e){
        this.player = e.getEntity().getKiller();
        assert player != null;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
