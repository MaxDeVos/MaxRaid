package maxdevos.maxraid.items;

import maxdevos.maxraid.items.armor.RaidArmor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMonster;
import org.bukkit.inventory.ItemStack;

public class Equipper {

    public static void setMobArmor(CraftMonster monster, RaidArmor armor){

        if(armor == null){
            monster.getEquipment().clear();
            return;
        }

        if(monster.getEquipment() != null){
            if(armor.helmet != null){
                monster.getEquipment().setHelmet(armor.helmet);
            }
            if(armor.chestplate != null){
                monster.getEquipment().setChestplate(armor.chestplate);
            }
            if(armor.leggings != null){
                monster.getEquipment().setLeggings(armor.leggings);
            }
            if(armor.boots != null){
                monster.getEquipment().setBoots(armor.boots);
            }
        }
    }

    public static void setMobWeapon(CraftMonster monster, ItemStack weapon){
        if(monster.getEquipment() != null){
            monster.getEquipment().setItemInMainHand(weapon);
        }
    }

}
