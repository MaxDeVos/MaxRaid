package maxdevos.maxraid.events;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.items.weapons.bows.RaidBow;
import maxdevos.maxraid.mobs.base.*;
import maxdevos.maxraid.mobs.experimental.*;
import maxdevos.maxraid.mobs.fleets.BomberFleet;
import maxdevos.maxraid.mobs.fleets.ParatrooperFleet;
import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.raid.SpawnZone;
import org.bukkit.Color;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.util.BlockVector;

import java.awt.*;

public class RaidProgressionEventHandler implements Listener {

    MaxRaid raid;
    SpawnZone north = new SpawnZone(-535, 553, -428, 483); //50%
    SpawnZone east = new SpawnZone(-381, 531, -428, 646); //30%
    SpawnZone south = new SpawnZone(-428, 611, -466,653); //12%
    SpawnZone west = new SpawnZone(-524, 573, -515,605); //8%

    private BlockVector getRandomSpawn(){
        double rand = Math.random();
        if(rand > 0.5){
            return north.getRandomSpawn();
        } else if(rand > 0.2){
            return east.getRandomSpawn();
        } else if(rand > 0.08){
            return south.getRandomSpawn();
        }
        return west.getRandomSpawn();
    }

    int wave = 0;

    public RaidProgressionEventHandler(MaxRaid raid){
        this.raid = raid;
        Server server = RaidPlugin.getServerInstance();
        RaidPlugin plugin = RaidPlugin.getInstance();
        server.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void startRaid(RaidTriggerEvent e){
        wave = 0;
    }


    @EventHandler
    public void newRaidWave(RaidSpawnWaveEvent waveEvent) {
        new RaidZombie(raid, new BlockVector(-460, 93, 617), 40f, Color.WHITE, null);
    }

//    @EventHandler
//    public void newRaidWave(RaidSpawnWaveEvent waveEvent){
//        wave++;
//        if(wave == 1){
//            GroundTroops gt = new GroundTroops();
//            gt.northBomberCreepers = 5;
//            gt.southBomberCreepers = 2;
//            gt.zombies = 4;
//            gt.witherSkels = 4;
//            gt.snipers = 0;
//            gt.suicidePhantoms = 0;
//            gt.endermen = 0;
//            gt.pillager = 2;
//            gt.ravager = 1;
//            gt.vindicator = 1;
//            gt.witch = 1;
//            gt.skel = 4;
//            gt.bombsPerSecond = 0.75;
//            gt.spawn();
//        }
//
//        if(wave == 2){
//            GroundTroops gt = new GroundTroops();
//            gt.northBomberCreepers = 5;
//            gt.southBomberCreepers = 3;
//            gt.zombies = 5;
//            gt.witherSkels = 4;
//            gt.snipers = 2;
//            gt.suicidePhantoms = 1;
//            gt.endermen = 0;
//            gt.pillager = 1;
//            gt.ravager = 3;
//            gt.vindicator = 2;
//            gt.witch = 2;
//            gt.skel = 5;
//            gt.bombsPerSecond = 0.8;
//            gt.spawn();
//
//            ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
//                new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 133, 593));
//            Fleet f1 = new Fleet(new BlockVector(-472, 133, 594));
//            f1.fuseless = 1;
//            f1.fullAuto = 1;
//            f1.endermen = 0;
//            f1.pillager = 0;
//            f1.ravager = 0;
//            f1.vindicator = 1;
//            f1.witch = 1;
//            f1.skel = 0;
//            f1.wallSeekers = 1;
//            f1.zombies = 1;
//            paraDropper.addFleet(f1.fleet);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);
//        }
//
//        if(wave == 3){
//            GroundTroops gt = new GroundTroops();
//            gt.northBomberCreepers = 2;
//            gt.southBomberCreepers = 1;
//            gt.zombies = 6;
//            gt.witherSkels = 5;
//            gt.snipers = 4;
//            gt.suicidePhantoms = 3;
//            gt.endermen = 1;
//            gt.pillager = 2;
//            gt.ravager = 3;
//            gt.vindicator = 2;
//            gt.witch = 2;
//            gt.skel = 2;
//            gt.bombsPerSecond = 1;
//            gt.spawn();
//
//            ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
//                    new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 133, 593));
//            Fleet f1 = new Fleet(new BlockVector(-492, 133, 586));
//            f1.fuseless = 2;
//            f1.fullAuto = 3;
//            f1.endermen = 0;
//            f1.pillager = 0;
//            f1.ravager = 0;
//            f1.vindicator = 0;
//            f1.witch = 2;
//            f1.skel = 2;
//            f1.wallSeekers = 3;
//            f1.zombies = 1;
//            f1.spawn();
//            paraDropper.addFleet(f1.fleet);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);
//        }
//
//        if(wave == 4){
//            GroundTroops gt = new GroundTroops();
//            gt.northBomberCreepers = 3;
//            gt.southBomberCreepers = 2;
//            gt.zombies = 4;
//            gt.witherSkels = 2;
//            gt.snipers = 5;
//            gt.suicidePhantoms = 5;
//            gt.endermen = 0;
//            gt.pillager = 5;
//            gt.ravager = 4;
//            gt.vindicator = 1;
//            gt.witch = 1;
//            gt.skel = 6;
//            gt.bombsPerSecond = 1.2;
//            gt.spawn();
//
//            ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
//                    new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 133, 593));
//            Fleet f1 = new Fleet(new BlockVector(-484, 133, 576));
//            f1.fuseless = 4;
//            f1.fullAuto = 4;
//            f1.endermen = 0;
//            f1.pillager = 0;
//            f1.ravager = 1;
//            f1.vindicator = 2;
//            f1.witch = 1;
//            f1.skel = 3;
//            f1.wallSeekers = 4;
//            f1.zombies = 2;
//            f1.spawn();
//            paraDropper.addFleet(f1.fleet);
//
//            Fleet f2 = new Fleet(new BlockVector(-481, 133, 602));
//            f2.fuseless = 4;
//            f2.fullAuto = 4;
//            f2.endermen = 0;
//            f2.pillager = 0;
//            f2.ravager = 1;
//            f2.vindicator = 2;
//            f2.witch = 1;
//            f2.skel = 3;
//            f2.wallSeekers = 4;
//            f2.zombies = 2;
//            f2.spawn();
//            paraDropper.addFleet(f2.fleet);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);
//        }
//
//        if(wave == 5){
//            GroundTroops gt = new GroundTroops();
//            gt.northBomberCreepers = 4;
//            gt.southBomberCreepers = 5;
//            gt.zombies = 3;
//            gt.witherSkels = 3;
//            gt.snipers = 5;
//            gt.suicidePhantoms = 5;
//            gt.endermen = 0;
//            gt.pillager = 5;
//            gt.ravager = 4;
//            gt.vindicator = 1;
//            gt.witch = 1;
//            gt.skel = 3;
//            gt.bombsPerSecond = 1.2;
//            gt.spawn();
//
//            ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
//                    new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 133, 593));
//            Fleet f1 = new Fleet(new BlockVector(-492, 133, 586));
//            f1.fuseless = 4;
//            f1.fullAuto = 4;
//            f1.endermen = 1;
//            f1.pillager = 1;
//            f1.ravager = 2;
//            f1.vindicator = 2;
//            f1.witch = 3;
//            f1.skel = 4;
//            f1.wallSeekers = 4;
//            f1.zombies = 4;
//            f1.spawn();
//            paraDropper.addFleet(f1.fleet);
//
//            Fleet f2 = new Fleet(new BlockVector(-453, 133, 599));
//            f2.fuseless = 4;
//            f2.fullAuto = 5;
//            f2.endermen = 1;
//            f2.pillager = 2;
//            f2.ravager = 1;
//            f2.vindicator = 2;
//            f2.witch = 1;
//            f2.skel = 4;
//            f2.wallSeekers = 4;
//            f2.zombies = 5;
//            f2.spawn();
//            paraDropper.addFleet(f2.fleet);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);
//
//            BunkerBuster b1 = new BunkerBuster(raid, new BlockVector(-485, 110, 588), 30, 0);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> b1.initiate(), 10 * 20L);
//        }
//
//        if(wave == 6){
//            GroundTroops gt = new GroundTroops();
//            gt.northBomberCreepers = 4;
//            gt.southBomberCreepers = 5;
//            gt.zombies = 3;
//            gt.witherSkels = 3;
//            gt.snipers = 5;
//            gt.suicidePhantoms = 5;
//            gt.endermen = 0;
//            gt.pillager = 5;
//            gt.ravager = 4;
//            gt.vindicator = 1;
//            gt.witch = 1;
//            gt.skel = 3;
//            gt.bombsPerSecond = 1.2;
//            gt.spawn();
//
//            ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
//                    new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 133, 593));
//            Fleet f1 = new Fleet(new BlockVector(-492, 133, 586));
//            f1.fuseless = 4;
//            f1.fullAuto = 4;
//            f1.endermen = 1;
//            f1.pillager = 1;
//            f1.ravager = 2;
//            f1.vindicator = 2;
//            f1.witch = 3;
//            f1.skel = 4;
//            f1.wallSeekers = 4;
//            f1.zombies = 4;
//            f1.spawn();
//            paraDropper.addFleet(f1.fleet);
//
//            Fleet f2 = new Fleet(new BlockVector(-453, 133, 592));
//            f2.fuseless = 4;
//            f2.fullAuto = 5;
//            f2.endermen = 1;
//            f2.pillager = 2;
//            f2.ravager = 1;
//            f2.vindicator = 2;
//            f2.witch = 1;
//            f2.skel = 4;
//            f2.wallSeekers = 4;
//            f2.zombies = 5;
//            f2.spawn();
//            paraDropper.addFleet(f2.fleet);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);
//
//            BunkerBuster b1 = new BunkerBuster(raid, new BlockVector(-466, 110, 588), 30, 1);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> b1.initiate(), 10 * 20L);
//        }
//
//        if(wave == 7){
//            GroundTroops gt = new GroundTroops();
//            gt.northBomberCreepers = 4;
//            gt.southBomberCreepers = 5;
//            gt.zombies = 3;
//            gt.witherSkels = 3;
//            gt.snipers = 5;
//            gt.suicidePhantoms = 5;
//            gt.endermen = 0;
//            gt.pillager = 5;
//            gt.ravager = 4;
//            gt.vindicator = 1;
//            gt.witch = 1;
//            gt.skel = 3;
//            gt.bombsPerSecond = 1.2;
//            gt.spawn();
//
//            ParatrooperDroppingPhantom paraDropper = new ParatrooperDroppingPhantom(raid,
//                    new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 133, 593));
//            Fleet f1 = new Fleet(new BlockVector(-475, 133, 588));
//            f1.fuseless = 4;
//            f1.fullAuto = 4;
//            f1.endermen = 1;
//            f1.pillager = 1;
//            f1.ravager = 2;
//            f1.vindicator = 2;
//            f1.witch = 3;
//            f1.skel = 4;
//            f1.wallSeekers = 4;
//            f1.zombies = 4;
//            f1.spawn();
//            paraDropper.addFleet(f1.fleet);
//
//            Fleet f2 = new Fleet(new BlockVector(-476, 133, 602));
//            f2.fuseless = 4;
//            f2.fullAuto = 5;
//            f2.endermen = 1;
//            f2.pillager = 2;
//            f2.ravager = 1;
//            f2.vindicator = 2;
//            f2.witch = 1;
//            f2.skel = 4;
//            f2.wallSeekers = 4;
//            f2.zombies = 5;
//            f2.spawn();
//            paraDropper.addFleet(f2.fleet);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper.spawn(), 5 * 20L);
//
//            ParatrooperDroppingPhantom paraDropper2 = new ParatrooperDroppingPhantom(raid,
//                    new BlockVector(raid.raidBase.zeroCorner.getX() - 20, 133, 593));
//            f1 = new Fleet(new BlockVector(-475, 133, 588));
//            f1.fuseless = 4;
//            f1.fullAuto = 4;
//            f1.endermen = 1;
//            f1.pillager = 1;
//            f1.ravager = 2;
//            f1.vindicator = 2;
//            f1.witch = 3;
//            f1.skel = 4;
//            f1.wallSeekers = 4;
//            f1.zombies = 4;
//            f1.spawn();
//            paraDropper2.addFleet(f1.fleet);
//
//            f2 = new Fleet(new BlockVector(-476, 133, 602));
//            f2.fuseless = 4;
//            f2.fullAuto = 5;
//            f2.endermen = 1;
//            f2.pillager = 2;
//            f2.ravager = 1;
//            f2.vindicator = 2;
//            f2.witch = 1;
//            f2.skel = 4;
//            f2.wallSeekers = 4;
//            f2.zombies = 5;
//            f2.spawn();
//            paraDropper2.addFleet(f2.fleet);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> paraDropper2.spawn(), 5 * 20L);
//
//
//            BunkerBuster b1 = new BunkerBuster(raid, new BlockVector(-476, 110, 592), 30, 2);
//            RaidPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () -> b1.initiate(), 14 * 20L);
//        }
//    }

    public void unregister(){
        HandlerList.unregisterAll(this);
    }

    private class GroundTroops {

        int northBomberCreepers = 5;
        int southBomberCreepers = 2;
        int zombies = 4;
        int witherSkels = 4;
        int snipers = 0;
        int suicidePhantoms = 0;
        int endermen = 0;
        int pillager = 2;
        int ravager = 1;
        int vindicator = 1;
        int witch = 1;
        int skel = 4;
        double bombsPerSecond = 0.75;
        public void spawn(){

            for(int i = 0; i < northBomberCreepers; i++){
                new WallSeekingCreeper(raid, north.getSpawnOnZAxis(541)); // North wall creepers
                new WallSeekingCreeper(raid, east.getSpawnOnXAxis(-416)); // East wall creepers
                if(i < southBomberCreepers){
                    new WallSeekingCreeper(raid, south.getRandomSpawn()); // East wall creepers
                    new WallSeekingCreeper(raid, west.getRandomSpawn()); // East wall creepers
                }
            }

            for(int i = 0; i < zombies; i++){
                new RaidZombie(raid, getRandomSpawn(), 40f, Color.WHITE, null);
            }

            for(int i = 0; i < witherSkels; i++){
                new RaidWitherSkeleton(raid, getRandomSpawn(), 40f,Color.WHITE, null);
            }

            for(int i = 0; i < snipers; i++){
                new SniperSkeleton(raid, getRandomSpawn());
            }

            for(int i = 0; i < suicidePhantoms; i++){
                new ExplodingPhantom(raid, getRandomSpawn());
            }

            for(int i = 0; i < endermen; i++){
                new RaidEnderman(raid, getRandomSpawn());
            }

            for(int i = 0; i < pillager; i++){
                new RaidPillager(raid, getRandomSpawn());
            }

            for(int i = 0; i < ravager; i++){
                new RaidRavager(raid, getRandomSpawn());
            }

            for(int i = 0; i < vindicator; i++){
                new RaidVindicator(raid, getRandomSpawn());
            }

            for(int i = 0; i < witch; i++){
                new RaidWitch(raid, getRandomSpawn());
            }

            for(int i = 0; i < skel; i++){
                new RaidSkeleton(raid, getRandomSpawn(), (float) (20 * (wave * 1.2)));
            }

            new BomberFleet(raid, 0.5, bombsPerSecond);
        }
    }

    private class Fleet {

        public ParatrooperFleet fleet;

        public Fleet(BlockVector loc){
            fleet = new ParatrooperFleet(loc);
        }

        int fuseless;
        int fullAuto = 5;
        int endermen = 0;
        int pillager = 2;
        int ravager = 1;
        int vindicator = 1;
        int witch = 1;
        int skel = 4;
        int wallSeekers = 0;
        int zombies = 0;
        public void spawn(){

            for(int i = 0; i < zombies; i++){
                fleet.addMobToFleet(new RaidZombie(raid));
            }

            for(int i = 0; i < wallSeekers; i++){
                fleet.addMobToFleet(new WallSeekingCreeper(raid));
            }

            for(int i = 0; i < fuseless; i++){
                fleet.addMobToFleet(new FuselessCreeper(raid));
            }

            for(int i = 0; i < endermen; i++){
                fleet.addMobToFleet(new RaidEnderman(raid));
            }

            for(int i = 0; i < pillager; i++){
                fleet.addMobToFleet(new RaidPillager(raid));
            }

            for(int i = 0; i < ravager; i++){
                fleet.addMobToFleet(new RaidRavager(raid));
            }

            for(int i = 0; i < vindicator; i++){
                fleet.addMobToFleet(new RaidVindicator(raid));
            }

            for(int i = 0; i < witch; i++){
                fleet.addMobToFleet(new RaidWitch(raid));
            }

            for(int i = 0; i < skel; i++){
                fleet.addMobToFleet(new RaidSkeleton(raid));
            }

            for(int i = 0; i < fullAuto; i++) {
                fleet.addMobToFleet(new FullAutoSkeleton(raid, 20* (wave * 1.2)));
            }


        }
    }
}
