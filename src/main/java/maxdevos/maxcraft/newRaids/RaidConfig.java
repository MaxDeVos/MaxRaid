package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.bukkit.ChatColor.*;

public class RaidConfig{

    private File raidSetupFile;
    private FileConfiguration raidSetup;
    private MaxPlugin plugin;
    private ArrayList<RaidWave> waves;

    public RaidConfig(MaxPlugin plugin, String raidName){
        this.plugin = plugin;
        waves = new ArrayList<>();
        raidSetupFile = new File(plugin.getDataFolder(), raidName);
        if (!raidSetupFile.exists()) {
            plugin.getServer().broadcastMessage(DARK_RED+ "[RAID ERROR] " + WHITE + "Raid Config File: " +
                    raidName + " not found.  Running default raid.");
            raidSetupFile = new File(plugin.getDataFolder(), "defaultRaid.yml");
        }

        raidSetup = new YamlConfiguration();
        try {
            raidSetup.load(raidSetupFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        for(int i=1;i<7;i++){
            RaidWave wave = new RaidWave();
            wave.setAirDrops(raidSetup.getInt(i+"."+"airdrops"));
        }

    }



}
