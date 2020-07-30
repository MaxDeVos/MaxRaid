package maxdevos.maxcraft;

import org.bukkit.plugin.java.JavaPlugin;

public final class MaxCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("fuckyou").setExecutor(new FuckYouCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
