package maxdevos.maxraid;

import maxdevos.maxraid.commands.*;
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


    private FileConfiguration customConfig;
    private static RaidPlugin plugin;

    @Override
    public void onEnable() {

        // Plugin startup logic
        plugin = this;
        createCustomConfig();
        new CatchAllListener();
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(getCommand("maxhelp")).setExecutor(new MaxHelp());
        Objects.requireNonNull(getCommand("nickname")).setExecutor(new NicknameCommand());
        Objects.requireNonNull(getCommand("maxv")).setExecutor(new MaxInvisible());
        Objects.requireNonNull(getCommand("endraid")).setExecutor(new EndRaidCommand());
        Objects.requireNonNull(getCommand("endwave")).setExecutor(new EndWaveCommand());
        Objects.requireNonNull(getCommand("setraid")).setExecutor(new SetRaidCommand());

        getServer().getPluginManager().registerEvents(this, this);
        plugin = this;

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
