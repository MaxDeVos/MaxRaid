package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.items.weapons.projecticles.BaseArrow;
import maxdevos.maxraid.items.weapons.projecticles.SniperArrow;
import maxdevos.maxraid.items.weapons.projecticles.TrampolineArrow;
import net.minecraft.server.level.ServerLevel;
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

        if(args.length == 0){
//            NMSRaid raid = RaidFactory.createOrExtendRaid(w, p);
//            sLevel.addFreshEntity(new NMSSpider(sLevel, new BlockVector(25, -55, 25)));
        }
        else if (args.length == 2){
//            new BaseArrow(sLevel.getPlayerByUUID(p.getUniqueId()).getBukkitEntity());
//            new SniperArrow(sLevel.getPlayerByUUID(p.getUniqueId()).getBukkitEntity());
            new TrampolineArrow(sLevel.getPlayerByUUID(p.getUniqueId()).getBukkitEntity());
        }
        else{
//            sLevel.addFreshEntity(new NMSSpider(sLevel, new BlockVector(25, -55, 25)));
        }


        return true;
    }
}