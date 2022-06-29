package maxdevos.maxraid.events;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.mobs.experimental.FullAutoSkeleton;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.util.BlockVector;

public class RaidProgressionEventHandler implements Listener {

    MaxRaid raid;

    public RaidProgressionEventHandler(MaxRaid raid){
        this.raid = raid;
        Server server = RaidPlugin.getServerInstance();
        RaidPlugin plugin = RaidPlugin.getInstance();
        server.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void newRaidWave(RaidSpawnWaveEvent waveEvent){

//        new LimboSpider(raid, new BlockVector(-453, 81, 590));

//        new BomberFleet(raid, 5.0, 1.3);
//
//
//        ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
//                new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 120, 593));
//
//        ParatrooperFleet fleet = new ParatrooperFleet(new BlockVector(-472, 120, 594));
//        fleet.addMobToFleet(new SniperSkeleton(raid));
//        fleet.addMobToFleet(new FuselessCreeper(raid));
//        fleet.addMobToFleet(new WallSeekingCreeper(raid));
//        fleet.addMobToFleet(new RaidWitherSkeleton(raid));
//        fleet.addMobToFleet(new RaidZombie(raid));
//        fleet.addMobToFleet(new RaidSpider(raid));
//        paraDropper.addFleet(fleet);
//
//        ParatrooperFleet fleet2 = new ParatrooperFleet(new BlockVector(-450, 120, 594));
//        fleet2.addMobToFleet(new SniperSkeleton(raid));
//        fleet2.addMobToFleet(new FuselessCreeper(raid));
//        fleet2.addMobToFleet(new WallSeekingCreeper(raid));
//        fleet2.addMobToFleet(new RaidPillager(raid));
//        fleet2.addMobToFleet(new RaidWitherSkeleton(raid));
//        fleet2.addMobToFleet(new RaidSpider(raid));
//        paraDropper.addFleet(fleet2);

//        paraDropper.spawn();

//         Start paradropper after bombers start
//        RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);


//        new WallSeekingCreeper(raid, new BlockVector(-454, 81, 550));
//        new WallSeekingCreeper(raid, new BlockVector(-460, 81, 550));
//        new WallSeekingCreeper(raid, new BlockVector(-465, 81, 550));
//        new SniperSkeleton(raid, new BlockVector(-480, 81, 585));
        new FullAutoSkeleton(raid, new BlockVector(50, 81, 50));

//        new RaidZombie(raid, new BlockVector(25, 3, 25));
//        new RaidSpider(raid, new BlockVector(25, -55, 25));
//        new RaidMagmaCube(raid, new BlockVector(6,-55,6));
//        new RaidSkeleton(raid, new BlockVector(-25, -55, -25));
    }

    public void unregister(){
        HandlerList.unregisterAll(this);
    }
}
