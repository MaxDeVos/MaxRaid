package maxdevos.maxcraft.raidSystem.ordinances;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.raidSystem.RaidPlayer;
import maxdevos.maxcraft.util.ChatFunctions;
import maxdevos.maxcraft.util.WorldUtils;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

@SuppressWarnings({"deprecation", "unused"})
public class RaidOrdinance {

    private Location l;

    public RaidOrdinance(RaidPlayer p) {
        this.l = WorldUtils.findReasonableLocation(p.getPlayer().getLocation(), 2);
        p.getPlayer().sendMessage(ChatFunctions.raidPrefix + "Your Ordiance Has Spawned!");
        l.getBlock().setType(Material.CHEST);
        Chest s = (Chest) l.getBlock().getState();
        s.getBlockInventory().addItem(new Excalibur(10).getItem(), new AWP(10).getItem());
        MaxPlugin.getServerInstance().getScheduler().scheduleSyncDelayedTask(MaxPlugin.getInstance(),
                this::generateNotifierEffect, 20L);
    }

    private void generateNotifierEffect(){
        Location fireworkLocation = l.add(0,1,0);
        fireworkLocation.getWorld().spawnEntity(fireworkLocation, EntityType.FIREWORK);

        Firework fw = (Firework) fireworkLocation.getWorld().spawnEntity(fireworkLocation, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(1);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(false).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

    }

}
