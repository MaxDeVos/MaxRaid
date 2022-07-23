package maxdevos.maxraid.commands;

import baritone.Baritone;
import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.items.weapons.projecticles.TrampolineArrow;
import maxdevos.maxraid.raid.NMSRaid;
import maxdevos.maxraid.raid.RaidFactory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftZombie;
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

//        NMSRaid raid = RaidFactory.createOrExtendRaid(w, p);


        Zombie creeper = new Zombie(EntityType.ZOMBIE, w.getHandle());
        creeper.goalSelector.removeAllGoals();
        creeper.targetSelector.removeAllGoals();
        creeper.setPos(-500, 75, 500);
        w.getHandle().addFreshEntity(creeper);
        ((CraftZombie) creeper.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.COBBLESTONE));


        Bukkit.getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(),() ->{

            Baritone b = new Baritone(creeper);
            b.spigotGameEventHandlerWrapper.loadWorld(w.getHandle());
            Location loc = RaidPlugin.savedLoc;
            b.getCommandManager().execute("goto " + loc.getX() + " " + loc.getY() + " " + loc.getZ());

        }, 10);

        return true;

    }
}