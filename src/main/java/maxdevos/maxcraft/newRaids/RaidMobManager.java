package maxdevos.maxcraft.newRaids;

import maxdevos.maxcraft.util.VectorTools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("deprecation")
class RaidMobManager {

    Random r;

    RaidMobManager(){
        r = new Random();
    }

    void spawnCustomZombie(World w, Player target, RaidSpawnWaveEvent e, boolean armoured){
        if(r.nextInt(4) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.ZOMBIE);

            if (armoured) {
                fullyEquip(mob, true);
            } else {
                giveHelmet(mob);
            }

            Zombie z = (Zombie) mob;
            z.setCustomName("§cPillager Zombie");
            z.setCustomNameVisible(true);
            z.setMaxHealth(30);
            z.setHealth(30);
            z.setTarget(target);
        }
    }

    void spawnCustomSkeleton(World w, Player target, RaidSpawnWaveEvent e, int wave, boolean armoured, boolean superBow){
        if(r.nextInt(4) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.SKELETON);

            if (armoured) {
                fullyEquip(mob, false);
            } else {
                giveHelmet(mob);
            }

            if (superBow) {
                giveSuperBow(mob);
            } else {
                ItemStack bow = new ItemStack(Material.BOW, 1);
                bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
                bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);

                EntityEquipment ee = mob.getEquipment();
                Objects.requireNonNull(ee).setHelmetDropChance(0.0F);
                ee.setItemInMainHand(bow);
            }

            Skeleton s = (Skeleton) mob;

            s.setCustomName("§cPillager Skeleton");
            s.setCustomNameVisible(true);
            s.setMaxHealth(wave * 5);
            s.setHealth(wave * 5);
            s.setTarget(target);
        }
    }

    void spawnCustomSpider(World w, Player target, RaidSpawnWaveEvent e, int wave){
        if(r.nextInt(4) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.SPIDER);
            Spider s = (Spider) mob;

            s.setCustomName("§cPillager Spider");
            s.setCustomNameVisible(true);
            s.setMaxHealth(wave * 5);
            s.setHealth(wave * 5);
            s.setTarget(target);
        }
    }

    void spawnCustomCreeper(World w, Player target, RaidSpawnWaveEvent e, int wave){
        if(r.nextInt(4) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.CREEPER);
            Creeper c = (Creeper) mob;

            c.setCustomName("§cPillager Creeper");
            c.setCustomNameVisible(true);
            c.setMaxHealth(wave * 5);
            c.setHealth(wave * 5);
            c.setTarget(target);
        }
    }

    void spawnCustomPillager(World w, Player target, RaidSpawnWaveEvent e){
        if(r.nextInt(4) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.PILLAGER);
            Pillager p = (Pillager) mob;

            p.setCanJoinRaid(true);
            p.setTarget(target);
            p.setMaxHealth(30);
            p.setHealth(30);
        }
    }

    void spawnCustomIllusioner(World w, Player target, RaidSpawnWaveEvent e){
        if(r.nextInt(8) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.ILLUSIONER);
            Illusioner i = (Illusioner) mob;

            i.setCanJoinRaid(true);
            i.setTarget(target);
            i.setMaxHealth(10);
            i.setHealth(10);
        }
    }

    void spawnCustomVex(World w, Player target, RaidSpawnWaveEvent e){
        LivingEntity mob = (LivingEntity)w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.VEX);
        Vex v = (Vex)mob;

        v.setTarget(target);
        v.setMaxHealth(5);
        v.setHealth(5);
    }

    void spawnCustomGhast(World w, Player target, RaidSpawnWaveEvent e){
        if(r.nextInt(5) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation().add(0, 20, 0), EntityType.GHAST);
            Ghast g = (Ghast) mob;

            g.setTarget(target);
            g.setMaxHealth(40);
            g.setHealth(40);
        }
    }

    void spawnCustomEnderman(World w, Player target, RaidSpawnWaveEvent e){
        if(r.nextInt(4) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.ENDERMAN);
            Enderman m = (Enderman) mob;

            m.setTarget(target);
            m.setMaxHealth(100);
            m.setHealth(100);
        }
    }

    void spawnCustomBlaze(World w, Player target, RaidSpawnWaveEvent e){
        if(r.nextInt(5) == 0) {
            LivingEntity mob = (LivingEntity) w.spawnEntity(Objects.requireNonNull(e.getPatrolLeader()).getLocation(), EntityType.BLAZE);
            Blaze b = (Blaze) mob;

            b.setTarget(target);
            b.setMaxHealth(40);
            b.setHealth(40);
        }
    }

    private void fullyEquip(LivingEntity mob, boolean hasSword) {
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);

        EntityEquipment ee = mob.getEquipment();
        Objects.requireNonNull(ee).setHelmetDropChance(0.0F);
        ee.setChestplateDropChance(0.0F);
        ee.setHelmet(helm);
        ee.setChestplate(chest);
        ee.setLeggings(pants);
        ee.setBoots(boots);
        if(hasSword){
            ee.setItemInMainHand(sword);
        }
    }

    private void giveHelmet(LivingEntity mob) {
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);

        EntityEquipment ee = mob.getEquipment();
        Objects.requireNonNull(ee).setHelmetDropChance(0.0F);
        ee.setHelmet(helm);

    }

    void spawnAirDropZombie(World w, Player target, Location l){
        Vector newVec = new Vector(l.getBlockX(),l.getBlockY(),l.getBlockZ()).add(VectorTools.generateRandomVector(2,5));
        l = new Location(w, newVec.getBlockX() + 0.0, newVec.getBlockY() + 0.0, newVec.getBlockZ() + 0.0);
        LivingEntity mob = (LivingEntity)w.spawnEntity(l, EntityType.ZOMBIE);

        fullyEquip(mob, true);

        Zombie z = (Zombie)mob;
        z.setCustomName("§cAirdrop Zombie");
        z.setCustomNameVisible(true);
        z.setMaxHealth(10);
        z.setHealth(10);
        z.setTarget(target);
    }

    void spawnAirDropCreeper(World w, Player target, Location l){

        Vector newVec = new Vector(l.getBlockX(),l.getBlockY(),l.getBlockZ()).add(VectorTools.generateRandomVector(2,5));
        l = new Location(w, newVec.getBlockX() + 0.0, newVec.getBlockY() + 0.0, newVec.getBlockZ() + 0.0);
        LivingEntity mob = (LivingEntity)w.spawnEntity(l, EntityType.CREEPER);

        Creeper c = (Creeper)mob;
        c.setCustomName("§cAirdrop Creeper");
        c.setCustomNameVisible(true);
        c.setMaxHealth(25);
        c.setHealth(25);
        c.setTarget(target);

    }

    void spawnAirDropSpider(World w, Player target, Location l){

        Vector newVec = new Vector(l.getBlockX(),l.getBlockY(),l.getBlockZ()).add(VectorTools.generateRandomVector(2,5));
        l = new Location(w, newVec.getBlockX() + 0.0, newVec.getBlockY() + 0.0, newVec.getBlockZ() + 0.0);
        LivingEntity mob = (LivingEntity)w.spawnEntity(l, EntityType.SPIDER);

        Spider s = (Spider)mob;
        s.setCustomName("§cAirdrop Spider");
        s.setCustomNameVisible(true);
        s.setMaxHealth(30);
        s.setHealth(30);
        s.setTarget(target);

    }

    void spawnAirDropSkeleton(World w, Player target, Location l){

        Vector newVec = new Vector(l.getBlockX(),l.getBlockY(),l.getBlockZ()).add(VectorTools.generateRandomVector(2,5));
        l = new Location(w, newVec.getBlockX() + 0.0, newVec.getBlockY() + 0.0, newVec.getBlockZ() + 0.0);
        LivingEntity mob = (LivingEntity)w.spawnEntity(l, EntityType.SKELETON);

        fullyEquip(mob, false);

        giveSuperBow(mob);

        Skeleton s = (Skeleton)mob;
        s.setCustomName("§cAirdrop Skeleton");
        s.setCustomNameVisible(true);
        s.setMaxHealth(15);
        s.setHealth(15);
        s.setTarget(target);

    }

    private void giveSuperBow(LivingEntity mob) {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);

        EntityEquipment ee = mob.getEquipment();
        Objects.requireNonNull(ee).setHelmetDropChance(0.0F);
        ee.setItemInMainHand(bow);
    }

}
