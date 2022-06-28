package maxdevos.maxraid.commands;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.items.RaidItemType;
import maxdevos.maxraid.items.armor.helmets.RaidHelmet;
import maxdevos.maxraid.items.weapons.projecticles.BaseArrow;
import maxdevos.maxraid.items.weapons.projecticles.SniperArrow;
import maxdevos.maxraid.items.weapons.projecticles.TrampolineArrow;
import maxdevos.maxraid.mobs.experimental.SniperSkeleton;
import maxdevos.maxraid.raid.NMSRaid;
import maxdevos.maxraid.raid.RaidFactory;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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