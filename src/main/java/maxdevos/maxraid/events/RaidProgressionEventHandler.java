package maxdevos.maxraid.events;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.mobs.base.*;
import maxdevos.maxraid.mobs.experimental.FullAutoSkeleton;
import maxdevos.maxraid.mobs.fleets.BomberFleet;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.util.BlockVector;

public class RaidProgressionEventHandler implements Listener {

    MaxRaid raid;
    private final RaidPlugin plugin = RaidPlugin.getInstance();

    public RaidProgressionEventHandler(MaxRaid raid){
        this.raid = raid;
        Server server = RaidPlugin.getServerInstance();
        server.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void newRaidWave(RaidSpawnWaveEvent waveEvent){
        new BomberFleet(raid, 5.0);
//        new FullAutoSkeleton(raid, new BlockVector(9, 5, 9));
//        new RaidZombie(raid, new BlockVector(25, 3, 25));
//        new RaidSpider(raid, new BlockVector(25, -55, 25));
//        new RaidMagmaCube(raid, new BlockVector(6,-55,6));
//        new RaidSkeleton(raid, new BlockVector(-25, -55, -25));
    }

    public void unregister(){
        HandlerList.unregisterAll(this);
    }
}
