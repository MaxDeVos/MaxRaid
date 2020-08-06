package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.newRaids.newRaidMods.*;
import maxdevos.maxcraft.util.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import java.util.ArrayList;

class RaidWave {

    private ArrayList<Player> players;
    private RaidSpawnWaveEvent e;
    private int waveNum = 0;
    private int airDrops = 0;
    int blazes = 0;
    private int creepers = 0;
    private int endermen = 0;
    private int evokers = 0;
    private int ghasts = 0;
    private int magmaCubes = 0;
    private int phantoms = 0;
    private int pillagers = 0;
    private int ravagers = 0;
    private int skeletons = 0;
    private int vindicators = 0;
    private int witherSkeletons = 0;
    private int zombies = 0;
    private int dropBlazes = 0;
    private int dropCreepers = 0;
    private int dropEndermen = 0;
    private int dropEvokers = 0;
    private int dropGhasts = 0;
    private int dropMagmaCubes = 0;
    private int dropPhantoms = 0;
    private int dropPillagers = 0;
    private int dropRavagers = 0;
    private int dropSkeletons = 0;
    private int dropVindicators = 0;
    private int dropWitherSkeletons = 0;
    private int dropZombies = 0;

    RaidWave(){
        players = new ArrayList<>();
    }

    void configWave(ArrayList<Player> players, RaidSpawnWaveEvent e){
        this.players = players;
        this.e = e;
    }

    void spawnWave(){
        for(int i = 0; i < blazes; i++){
            new RaidBlaze(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < creepers; i++){
            new RaidCreeper(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < endermen; i++){
            new RaidEnderman(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < evokers; i++){
            new RaidEvoker(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < ghasts; i++){
            new RaidGhast(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < magmaCubes; i++){
            new RaidMagmaCube(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < phantoms; i++){
            new RaidPhantom(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < pillagers; i++){
            new RaidPillager(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < ravagers; i++){
            new RaidRavager(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < skeletons; i++){
            new RaidSkeleton(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < vindicators; i++){
            new RaidVindicator(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < witherSkeletons; i++){
            new RaidWitherSkeleton(PlayerUtils.getRandomPlayer(players),e);
        }
        for(int i = 0; i < zombies; i++){
            new RaidZombie(PlayerUtils.getRandomPlayer(players),e);
        }
    }

    void spawnAirdrops(){
        for(int f = 0; f < airDrops; f++){
            System.out.println(ChatColor.DARK_RED + "[MaxCraft] " + ChatColor.WHITE + "Airdrop");
            Player p = PlayerUtils.getRandomPlayer(players);
            for(int i = 0; i < dropBlazes; i++){
                new RaidBlaze(p);
            }
            for(int i = 0; i < dropCreepers; i++){
                new RaidCreeper(p);
            }
            for(int i = 0; i < dropEndermen; i++){
                new RaidEnderman(p);
            }
            for(int i = 0; i < dropEvokers; i++){
                new RaidEvoker(p);
            }
            for(int i = 0; i < dropGhasts; i++){
                new RaidGhast(p);
            }
            for(int i = 0; i < dropMagmaCubes; i++){
                new RaidMagmaCube(p);
            }
            for(int i = 0; i < dropPhantoms; i++){
                new RaidPhantom(p);
            }
            for(int i = 0; i < dropPillagers; i++){
                new RaidPillager(p);
            }
            for(int i = 0; i < dropRavagers; i++){
                new RaidRavager(p);
            }
            for(int i = 0; i < dropSkeletons; i++){
                new RaidSkeleton(p);
            }
            for(int i = 0; i < dropVindicators; i++){
                new RaidVindicator(p);
            }
            for(int i = 0; i < dropWitherSkeletons; i++){
                new RaidWitherSkeleton(p);
            }
            for(int i = 0; i < dropZombies; i++){
                new RaidZombie(p);
            }
        }
    }

    void setWaveNum(int waveNum){
        this.waveNum = waveNum;
    }

    void setDropBlazes(int dropBlazes) {
        this.dropBlazes = dropBlazes;
    }

    void setDropCreepers(int dropCreepers) {
        this.dropCreepers = dropCreepers;
    }

    void setDropEndermen(int dropEndermen) {
        this.dropEndermen = dropEndermen;
    }

    void setDropEvokers(int dropEvokers) {
        this.dropEvokers = dropEvokers;
    }

    void setDropGhasts(int dropGhasts) {
        this.dropGhasts = dropGhasts;
    }

    void setDropMagmaCubes(int dropMagmaCubes) {
        this.dropMagmaCubes = dropMagmaCubes;
    }

    void setDropPhantoms(int dropPhantoms) {
        this.dropPhantoms = dropPhantoms;
    }

    void setDropPillagers(int dropPillagers) {
        this.dropPillagers = dropPillagers;
    }

    void setDropRavagers(int dropRavagers) {
        this.dropRavagers = dropRavagers;
    }

    void setDropSkeletons(int dropSkeletons) {
        this.dropSkeletons = dropSkeletons;
    }

    void setDropVindicators(int dropVindicators) {
        this.dropVindicators = dropVindicators;
    }

    void setDropWitherSkeletons(int dropWitherSkeletons) {
        this.dropWitherSkeletons = dropWitherSkeletons;
    }

    void setDropZombies(int dropZombies) {
        this.dropZombies = dropZombies;
    }

    void setAirDrops(int airDrops) {
        this.airDrops = airDrops;
    }

    void setBlazes(int blazes) {
        this.blazes = blazes;
    }

    void setCreepers(int creepers) {
        this.creepers = creepers;
    }

    void setEndermen(int endermen) {
        this.endermen = endermen;
    }

    void setEvokers(int evokers) {
        this.evokers = evokers;
    }

    void setGhasts(int ghasts) {
        this.ghasts = ghasts;
    }

    void setMagmaCubes(int magma_cubes) {
        this.magmaCubes = magma_cubes;
    }

    void setPhantoms(int phantoms) {
        this.phantoms = phantoms;
    }

    void setPillagers(int pillagers) {
        this.pillagers = pillagers;
    }

    void setRavagers(int ravagers) {
        this.ravagers = ravagers;
    }

    void setSkeletons(int skeletons) {
        this.skeletons = skeletons;
    }

    void setVindicators(int vindicators) {
        this.vindicators = vindicators;
    }

    void setWitherSkeletons(int wither_skeletons) {
        this.witherSkeletons = wither_skeletons;
    }

    void setZombies(int zombies) {
        this.zombies = zombies;
    }

}
