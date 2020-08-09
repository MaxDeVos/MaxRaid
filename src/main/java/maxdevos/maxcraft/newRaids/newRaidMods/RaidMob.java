package maxdevos.maxcraft.newRaids.newRaidMods;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.util.VectorTools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Objects;

@SuppressWarnings("unused")
public class RaidMob implements Listener {

    private final LivingEntity m;
    private final Location spawnLocation;
    final Player target;
    private MaxPlugin plugin;

    RaidMob(Player target, RaidSpawnWaveEvent w, EntityType mobType){

        this.target = target;
        spawnLocation = Objects.requireNonNull(w.getPatrolLeader()).getLocation().add(new Vector(0,1,0));
        m = (LivingEntity)target.getWorld().spawnEntity(spawnLocation, mobType);
        setParams(m);
        this.plugin = MaxPlugin.getInstance();
        MaxPlugin.getServerInstance().getPluginManager().registerEvents(this, plugin);
    }

    RaidMob(Player target, EntityType mobType){

        Vector randVec = VectorTools.generateRandomVector(2,5);

        this.target = target;
        spawnLocation = target.getLocation().add(randVec).add(0,10,0);
        m = (LivingEntity)target.getWorld().spawnEntity(spawnLocation, mobType);
        setParams(m);
        this.plugin = MaxPlugin.getInstance();
        MaxPlugin.getServerInstance().getPluginManager().registerEvents(this, plugin);
    }

    RaidMob(Player target, Location spawnLocation, EntityType mobType){

        this.target = target;
        this.spawnLocation = spawnLocation;
        m = (LivingEntity)target.getWorld().spawnEntity(spawnLocation, mobType);
        setParams(m);
        this.plugin = MaxPlugin.getInstance();
        MaxPlugin.getServerInstance().getPluginManager().registerEvents(this, plugin);
    }

    void setParams(LivingEntity e) {

    }

    public LivingEntity getEntity(){
        return m;
    }

    public enum ArmorType {DIAMOND, GOLD, IRON, CHAIN, LEATHER}
    public enum WeaponType {SWORD, AXE, BOW}


    public void giveArmor(ArmorType armorType){

        EntityEquipment ee = m.getEquipment();

        ItemStack helm;
        ItemStack chest;
        ItemStack pants;
        ItemStack boots;

        if(armorType.equals(ArmorType.DIAMOND)) {
            helm = new ItemStack(Material.DIAMOND_HELMET, 1);
            chest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
            pants = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        }
        else if(armorType.equals(ArmorType.GOLD)){
            helm = new ItemStack(Material.GOLDEN_HELMET, 1);
            chest = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
            pants = new ItemStack(Material.GOLDEN_LEGGINGS, 1);
            boots = new ItemStack(Material.GOLDEN_BOOTS, 1);
        }
        else if(armorType.equals(ArmorType.IRON)){
            helm = new ItemStack(Material.IRON_HELMET, 1);
            chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
            pants = new ItemStack(Material.IRON_LEGGINGS, 1);
            boots = new ItemStack(Material.IRON_BOOTS, 1);
        }
        else if(armorType.equals(ArmorType.CHAIN)){
            helm = new ItemStack(Material.CHAINMAIL_HELMET, 1);
            chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
            pants = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
            boots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        }
        else if(armorType.equals(ArmorType.LEATHER)){
            helm = new ItemStack(Material.LEATHER_HELMET, 1);
            chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            pants = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        }
        else{
            return;
        }

        Objects.requireNonNull(ee).setHelmetDropChance(0.0F);
        ee.setChestplateDropChance(0.0F);
        ee.setHelmet(helm);
        ee.setChestplate(chest);
        ee.setLeggings(pants);
        ee.setBoots(boots);
    }

    public void giveWeapon(WeaponType weaponType){

        EntityEquipment ee = m.getEquipment();
        ItemStack weapon;

        switch (weaponType){
            case SWORD:
                weapon = new ItemStack(Material.DIAMOND_SWORD, 1);
                break;
            case AXE:
                weapon = new ItemStack(Material.DIAMOND_AXE, 1);
                break;
            case BOW:
                weapon = new ItemStack(Material.BOW, 1);
                weapon.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
                weapon.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + weaponType);
        }

        assert ee != null;
        ee.setItemInMainHand(weapon);

    }

    public void giveLeatherHelmet(){
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
        EntityEquipment ee = m.getEquipment();
        Objects.requireNonNull(ee).setHelmetDropChance(0.0F);
        ee.setHelmet(helm);
    }

    public void kill(){
        m.setHealth(0);
    }

}