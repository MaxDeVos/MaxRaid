package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.mobs.nmsMobs.RaidCreeper;
import maxdevos.maxraid.raid.NMSRaid;
import maxdevos.maxraid.raid.RaidFactory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;

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

        NMSRaid raid = RaidFactory.createOrExtendRaid(w, p);

        return true;
    }
}