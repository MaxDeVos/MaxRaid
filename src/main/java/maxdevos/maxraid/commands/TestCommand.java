package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.items.weapons.projecticles.TrampolineArrow;
import maxdevos.maxraid.raid.NMSRaid;
import maxdevos.maxraid.raid.RaidFactory;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
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
//           ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
//           helmet.setAmount(1);
//           helmet.getItemMeta().addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("GENERIC_ARMOR", 100, AttributeModifier.Operation.ADD_NUMBER));
//           p.getEquipment().setHelmet(helmet);
//            new BunkerBuster(p.getWorld(), p.getTargetBlockExact(5).getLocation().toVector().toBlockVector(), 20, Integer.parseInt(args[0])).initiate();
            NMSRaid raid = RaidFactory.createOrExtendRaid(w, p);
//            new SniperSkeleton(raid, ((Player) sender).getEyeLocation().blo);

        }
        else if (args.length == 2){
//            new BaseArrow(sLevel.getPlayerByUUID(p.getUniqueId()).getBukkitEntity());
//            new SniperArrow(sLevel.getPlayerByUUID(p.getUniqueId()).getBukkitEntity());
            new TrampolineArrow(sLevel.getPlayerByUUID(p.getUniqueId()).getBukkitEntity()).shootWhereLooking(1f, 0.0001f);
        }
        else{
//            sLevel.addFreshEntity(new NMSSpider(sLevel, new BlockVector(25, -55, 25)));
        }


        return true;
    }
}