package maxdevos.maxraid;

import baritone.api.Spigot;
import maxdevos.maxraid.commands.*;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public final class RaidPlugin extends JavaPlugin implements Listener {


    public static Location savedLoc;
    private FileConfiguration customConfig;
    private static RaidPlugin plugin;

    @Override
    public void onEnable() {

        // Plugin startup logic
        plugin = this;
        Spigot.setPlugin(this);
        createCustomConfig();
        new CatchAllListener();
        Objects.requireNonNull(getCommand("r")).setExecutor(new ResetCommand());
        Objects.requireNonNull(getCommand("reset")).setExecutor(new ResetCommand());
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(getCommand("endraid")).setExecutor(new EndRaidCommand());
        Objects.requireNonNull(getCommand("endwave")).setExecutor(new EndWaveCommand());
        Objects.requireNonNull(getCommand("pos")).setExecutor(new PosCommand());
        
        getServer().getPluginManager().registerEvents(this, this);

        savedLoc = new Location(getServerInstance().getWorlds().get(0), -500, 74, 500);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RaidPlugin getInstance(){
        return plugin;
    }
    public static Server getServerInstance(){
        return plugin.getServer();
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unused")
    void saveCustomConfig(){
        try {
            customConfig.save(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
