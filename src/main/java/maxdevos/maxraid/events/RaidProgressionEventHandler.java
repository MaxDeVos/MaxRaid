package maxdevos.maxraid.events;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.mobs.base.*;
import maxdevos.maxraid.mobs.experimental.*;
import maxdevos.maxraid.mobs.fleets.BomberFleet;
import maxdevos.maxraid.mobs.fleets.ParatrooperFleet;
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


        new BomberFleet(raid, 5.0, 0.8);


        ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
                new BlockVector(raid.raidBase.zeroCorner.getX() - 60, 120, 593));

        ParatrooperFleet fleet = new ParatrooperFleet(new BlockVector(-472, 120, 594));
        fleet.addMobToFleet(new SniperSkeleton(raid));
        fleet.addMobToFleet(new FuselessCreeper(raid));
        fleet.addMobToFleet(new WallSeekingCreeper(raid));
        paraDropper.addFleet(fleet);

        ParatrooperFleet fleet2 = new ParatrooperFleet(new BlockVector(-450, 120, 594));
        fleet2.addMobToFleet(new SniperSkeleton(raid));
        fleet2.addMobToFleet(new FuselessCreeper(raid));
        fleet2.addMobToFleet(new WallSeekingCreeper(raid));
        paraDropper.addFleet(fleet2);

        // Start paradropper after bombers start
        RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);

        new FullAutoSkeleton(raid, new BlockVector(-450, 100, 550));

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
