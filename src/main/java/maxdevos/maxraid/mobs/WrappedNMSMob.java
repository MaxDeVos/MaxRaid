package maxdevos.maxraid.mobs;

import org.bukkit.Location;

/** Interface to isolate NMS from Spigot system */
public interface WrappedNMSMob {

    public void spawn(Location l);
    public void kill();
    public void remove();

}