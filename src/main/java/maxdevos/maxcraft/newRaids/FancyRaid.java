package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.util.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class FancyRaid implements Listener {

    private MaxPlugin plugin;
    private RaidMobManager rmm;
    private Raid raid;
    private World w;
    private int wave = 0;
    private Random r;
    ArrayList<Player> players = new ArrayList<>();

    public FancyRaid(MaxPlugin plugin, Raid raid){

        rmm = new RaidMobManager();
        r = new Random();
        this.plugin = plugin;
        this.raid = raid;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.w = plugin.getServer().getWorlds().get(0);
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.LIGHT_PURPLE + "YOU'RE IN MY TURF NOW MOTHERFUCKERS.");
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.LIGHT_PURPLE + "YOU'RE GONNA REGRET LEAVING LAST NIGHT");
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "This Raid is sponsored by RAID Shadow Legends");

    }

    @EventHandler
    private void newWave(RaidSpawnWaveEvent e){

        System.out.println("Wave #: " + wave);
        raid.getHeroes();
        ArrayList<Player> players = new ArrayList<>();
        for (UUID d:raid.getHeroes()) {
                Player p = plugin.getServer().getPlayer(d);
                players.add(p);
                for (int i = 1; i < wave; i++) {
                    rmm.spawnCustomZombie(w, p, e, false);
                    rmm.spawnCustomSkeleton(w, p, e, wave, false, false);
                    rmm.spawnCustomSkeleton(w, p, e, wave, false, true);
                    rmm.spawnCustomPillager(w, p, e);
                    rmm.spawnCustomCreeper(w, p, e, wave);
                    rmm.spawnCustomSpider(w, p, e, wave);
                }
                if (wave > 2) {
                    rmm.spawnCustomZombie(w, p, e, true);
                    rmm.spawnCustomSkeleton(w, p, e, wave, true, true);
                    rmm.spawnCustomIllusioner(w, p, e);
                }
                if (wave > 3) {
                    rmm.spawnCustomGhast(w, p, e);
                }
                if (wave > 4) {
                    rmm.spawnCustomEnderman(w, p, e);
                }
                if (wave > 5) {
                    rmm.spawnCustomBlaze(w, p, e);
                }
                players.add(p);
            }
            if (wave > 2) {
                spawnAirDrop(PlayerUtils.getRandomPlayer(players));
            }
            if (wave > 5) {
                spawnAirDrop(PlayerUtils.getRandomPlayer(players));
            }
        wave++;
        if(wave == 1){
            this.players = players;
        }
    }

    @EventHandler
    private void killedIllager(EntityDeathEvent e){

        if(e.getEntity().getType().equals(EntityType.PILLAGER) || e.getEntity().getType().equals(EntityType.RAVAGER) ||
                e.getEntity().getType().equals(EntityType.EVOKER)){
            if(r.nextInt(1) == 0){
                Entity killer = e.getEntity().getLastDamageCause().getEntity();
                if(killer instanceof Player) {
                    Player p = (Player) killer;
                    plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "The death of that "
                            + e.getEntity().getName() + " will be avenged!");
                    spawnAirDrop(p);
                }
            }
        }
    }

    private void spawnAirDrop(Player p){

        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "AIRDROP INCOMING ON "
                + p.getDisplayName());
        Location l = p.getLocation().add(0,15,0);

        int tempWave;
        if(wave > 4){
            tempWave = 4;
        }
        else{
            tempWave = wave;
        }

        for(int i = 2; i < tempWave; i++){
            rmm.spawnAirDropZombie(w,p,l);
            rmm.spawnAirDropCreeper(w,p,l);
            rmm.spawnAirDropSkeleton(w,p,l);
            rmm.spawnAirDropSpider(w,p,l);
        }

    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity ent = event.getEntity();

        if (ent instanceof Fireball) {
            event.setCancelled(true);
            if (wave != 7) {
                w.createExplosion(ent.getLocation(), 2);
            } else {
                w.createExplosion(ent.getLocation(), 7);
            }
        }
    }

    @EventHandler
    private void endRaid(RaidFinishEvent e){
        System.out.println(ChatColor.DARK_RED + "[MaxCraft RAID] " + ChatColor.WHITE + "The Raid is Over.");
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    private void friendlyFire(EntityTargetEvent e) {
        if (!(e instanceof Player) && !(e.getTarget() instanceof Player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void antiExplosion(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void antiVex(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) {
            if(e.getDamager().getType().equals(EntityType.VEX)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void antiGolem(EntityTargetLivingEntityEvent e){
        if(e.getEntityType().equals(EntityType.IRON_GOLEM)) {
            if(e.getTarget() instanceof Player){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void antiVex2(EntitySpawnEvent e){
        if(e.getEntityType().equals(EntityType.VEX)){
            e.setCancelled(true);
        }
    }
}
