package maxdevos.maxcraft;

import maxdevos.maxcraft.commands.*;
import maxdevos.maxcraft.tedcruz.TedCruzTwitterTask;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class MaxPlugin extends JavaPlugin implements Listener{

    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        createCustomConfig();
        new CatchAllListener(this);
        Objects.requireNonNull(getCommand("fuckyou")).setExecutor(new FuckYouCommand(this));
        Objects.requireNonNull(getCommand("lag")).setExecutor(new LagCommand(this));
        Objects.requireNonNull(getCommand("maxhelp")).setExecutor(new MaxHelp(this));
        Objects.requireNonNull(getCommand("nickname")).setExecutor(new NicknameCommand(this));
        Objects.requireNonNull(getCommand("maxv")).setExecutor(new MaxInvisible(this));
        Objects.requireNonNull(getCommand("endraid")).setExecutor(new EndRaidCommand(this));
        Objects.requireNonNull(getCommand("endwave")).setExecutor(new EndWaveCommand(this));
        Objects.requireNonNull(getCommand("setraid")).setExecutor(new SetRaidCommand(this));

        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
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

    void saveCustomConfig(){
        try {
            customConfig.save(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
