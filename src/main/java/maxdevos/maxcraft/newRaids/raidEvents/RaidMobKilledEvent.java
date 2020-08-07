package maxdevos.maxcraft.newRaids.raidEvents;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;

public class RaidMobKilledEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    public Player player;
    public LivingEntity mob;

    public RaidMobKilledEvent(EntityDeathEvent e){
        this.mob = e.getEntity();
        this.player = e.getEntity().getKiller();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
