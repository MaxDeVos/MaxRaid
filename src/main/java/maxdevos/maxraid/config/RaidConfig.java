package maxdevos.maxraid.config;

import maxdevos.maxraid.RaidPlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.bukkit.ChatColor.DARK_RED;
import static org.bukkit.ChatColor.WHITE;

class RaidConfig{

    private final ArrayList<RaidWave> waves;
    private String raidName = "Default";
    private static final RaidPlugin plugin = RaidPlugin.getInstance();

    RaidConfig(String raidName){
        waves = new ArrayList<>();
        File raidSetupFile = new File(plugin.getDataFolder(), raidName + ".yml");
        if (!raidSetupFile.exists()) {
            plugin.getServer().broadcastMessage(DARK_RED+ "[RAID ERROR] " + WHITE + "Raid Config File: " +
                    raidName + " not found.  Running default raid.");
            raidSetupFile = new File(plugin.getDataFolder(), "defaultRaid.yml");
            if (!raidSetupFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                raidSetupFile.getParentFile().mkdirs();
                plugin.saveResource("defaultRaid.yml", false);
            }
        }

        FileConfiguration raidSetup = new YamlConfiguration();
        try {
            raidSetup.load(raidSetupFile);
            this.raidName = raidName;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        waves.add(new RaidWave());

        for(int i=2;i<=7;i++){
            System.out.println("Loading Wave " + i + " into memory.");
            RaidWave wave = new RaidWave();

            //Wave Mobs
            wave.setAirDrops(raidSetup.getInt("waves."+i+".airdrops"));
            wave.setBlazes(raidSetup.getInt("waves."+i+".wave-mobs."+"blaze"));
            wave.setCreepers(raidSetup.getInt("waves."+i+".wave-mobs."+"creeper"));
            wave.setEndermen(raidSetup.getInt("waves."+i+".wave-mobs."+"enderman"));
            wave.setEvokers(raidSetup.getInt("waves."+i+".wave-mobs."+"evoker"));
            wave.setGhasts(raidSetup.getInt("waves."+i+".wave-mobs."+"ghast"));
            wave.setMagmaCubes(raidSetup.getInt("waves."+i+".wave-mobs."+"magma-cube"));
            wave.setPhantoms(raidSetup.getInt("waves."+i+".wave-mobs."+"phantom"));
            wave.setPillagers(raidSetup.getInt("waves."+i+".wave-mobs."+"pillager"));
            wave.setRavagers(raidSetup.getInt("waves."+i+".wave-mobs."+"ravager"));
            wave.setSkeletons(raidSetup.getInt("waves."+i+".wave-mobs."+"skeleton"));
            wave.setVindicators(raidSetup.getInt("waves."+i+".wave-mobs."+"vindicator"));
            wave.setWitherSkeletons(raidSetup.getInt("waves."+i+".wave-mobs."+"wither-skeleton"));
            wave.setZombies(raidSetup.getInt("waves."+i+".wave-mobs."+"zombie"));

            //Airdrop Mobs
            wave.setDropBlazes(raidSetup.getInt("waves."+i+".airdrop-mobs."+"blaze"));
            wave.setDropCreepers(raidSetup.getInt("waves."+i+".airdrop-mobs."+"creeper"));
            wave.setDropEndermen(raidSetup.getInt("waves."+i+".airdrop-mobs."+"enderman"));
            wave.setDropEvokers(raidSetup.getInt("waves."+i+".airdrop-mobs."+"evoker"));
            wave.setDropGhasts(raidSetup.getInt("waves."+i+".airdrop-mobs."+"ghast"));
            wave.setDropMagmaCubes(raidSetup.getInt("waves."+i+".airdrop-mobs."+"magma-cube"));
            wave.setDropPhantoms(raidSetup.getInt("waves."+i+".airdrop-mobs."+"phantom"));
            wave.setDropPillagers(raidSetup.getInt("waves."+i+".airdrop-mobs."+"pillager"));
            wave.setDropRavagers(raidSetup.getInt("waves."+i+".airdrop-mobs."+"ravager"));
            wave.setDropSkeletons(raidSetup.getInt("waves."+i+".airdrop-mobs."+"skeleton"));
            wave.setDropVindicators(raidSetup.getInt("waves."+i+".airdrop-mobs."+"vindicator"));
            wave.setDropWitherSkeletons(raidSetup.getInt("waves."+i+".airdrop-mobs."+"wither-skeleton"));
            wave.setDropZombies(raidSetup.getInt("waves."+i+".airdrop-mobs."+"zombie"));
            waves.add(wave);
        }
    }

    RaidWave getWave(int wave){
        return(waves.get(wave-1));
    }

    String getRaidName(){
        return raidName;
    }

}
