package maxdevos.maxraid.events.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StopRaidEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
