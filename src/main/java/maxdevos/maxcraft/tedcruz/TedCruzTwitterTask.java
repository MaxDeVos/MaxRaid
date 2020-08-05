package maxdevos.maxcraft.tedcruz;

import maxdevos.maxcraft.MaxPlugin;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TedCruzTwitterTask extends BukkitRunnable {
    MaxPlugin plugin;

    public TedCruzTwitterTask(MaxPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        String prefix;
        String suffix;

        boolean rnd = new Random().nextBoolean();
        if(rnd) {
            prefix = "[TED CRUZ TWEET] ";

        }
        else{
            prefix = "[DRIL TWEET] ";
        }

        rnd = new Random().nextBoolean();
        if(rnd) {
            suffix = TedCruzTweetFactory.getRandomTweetCommand("tedcruz");


        }
        else{
            suffix = TedCruzTweetFactory.getRandomTweetCommand("dril");
        }


        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + prefix + ChatColor.AQUA + suffix);
    }


}