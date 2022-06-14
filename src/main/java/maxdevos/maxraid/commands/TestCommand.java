package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.mobs.experimental.BunnyphillicWolf;
import maxdevos.maxraid.mobs.experimental.WolfphillicRabbit;
import maxdevos.maxraid.util.PlayerUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    private final RaidPlugin plugin = RaidPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("test")) {
            return false;
        }

        Player p = (Player) sender;
        CraftWorld w = ((CraftWorld)p.getWorld());
        ServerLevel sLevel = w.getHandle();
        ServerPlayer NMSPlayer = PlayerUtils.getNMSPlayerFromBukkitPlayer(sLevel, p);
//        sLevel.getRaids().createOrExtendRaid(NMSPlayer);
//        p.sendMessage("eat my dick");
//        NMSSpider nmsSpider = new NMSSpider(sLevel);
//        NMSCreeper nmsCreeper = new NMSCreeper(sLevel);
//        sLevel.addFreshEntity(nmsSpider, CreatureSpawnEvent.SpawnReason.COMMAND);
        sLevel.addFreshEntity(new BunnyphillicWolf(sLevel));
        sLevel.addFreshEntity(new WolfphillicRabbit(sLevel));
//        sLevel.addFreshEntity(nmsCreeper, CreatureSpawnEvent.SpawnReason.COMMAND);
//        nmsCreeper.setTarget(nmsSpider);
        return true;
    }
}