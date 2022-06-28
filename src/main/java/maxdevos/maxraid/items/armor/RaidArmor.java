package maxdevos.maxraid.items.armor;

import maxdevos.maxraid.items.RaidItemType;
import maxdevos.maxraid.items.armor.boots.RaidBoots;
import maxdevos.maxraid.items.armor.chestplates.RaidChestplate;
import maxdevos.maxraid.items.armor.helmets.RaidHelmet;
import maxdevos.maxraid.items.armor.leggings.RaidLeggings;
import org.bukkit.Color;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMonster;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RaidArmor {

    public RaidHelmet helmet;
    public RaidChestplate chestplate;
    public RaidLeggings leggings;
    public RaidBoots boots;

    public RaidArmor(RaidItemType.ArmorMaterial material){
        helmet = new RaidHelmet(material);
        chestplate = new RaidChestplate(material);
        leggings = new RaidLeggings(material);
        boots = new RaidBoots(material);
    }

    public RaidArmor(Color color){
        helmet = new RaidHelmet(color);
        chestplate = new RaidChestplate(color);
        leggings = new RaidLeggings(color);
        boots = new RaidBoots(color);
    }

}