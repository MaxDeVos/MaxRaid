package maxdevos.maxraid.items.armor;

import maxdevos.maxraid.items.RaidItemType;
import maxdevos.maxraid.items.armor.boots.RaidBoots;
import maxdevos.maxraid.items.armor.chestplates.RaidChestplate;
import maxdevos.maxraid.items.armor.helmets.RaidHelmet;
import maxdevos.maxraid.items.armor.leggings.RaidLeggings;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftMonster;

public class RaidArmor {

    RaidHelmet helmet;
    RaidChestplate chestplate;
    RaidLeggings leggings;
    RaidBoots boots;

    RaidArmor(RaidItemType.ArmorMaterial material){
        helmet = new RaidHelmet(material);
        chestplate = new RaidChestplate(material);
        leggings = new RaidLeggings(material);
        boots = new RaidBoots(material);
    }

    // This is a bit silly, but due to inheritance problems with NMS, my hand is forced
    public void applyToMob(CraftMonster monster){
        if(monster.getEquipment() != null){
            if(helmet != null){
                monster.getEquipment().setHelmet(helmet);
            }
            if(chestplate != null){
                monster.getEquipment().setChestplate(chestplate);
            }
            if(leggings != null){
                monster.getEquipment().setLeggings(leggings);
            }
            if(boots != null){
                monster.getEquipment().setBoots(boots);
            }
        }
    }
}