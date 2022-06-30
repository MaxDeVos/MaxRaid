package maxdevos.maxraid.items.armor.boots;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RaidBoots extends ItemStack {

    public RaidBoots(RaidItemType.ArmorMaterial material){
        setAmount(1);
        switch (material){
            case LEATHER -> setType(Material.LEATHER_BOOTS);
            case GOLD -> setType(Material.GOLDEN_BOOTS);
            case CHAINMAIL -> setType(Material.CHAINMAIL_BOOTS);
            case IRON -> setType(Material.IRON_BOOTS);
            case DIAMOND -> setType(Material.DIAMOND_BOOTS);
            case NETHERITE -> setType(Material.NETHERITE_BOOTS);
        }
    }

    public RaidBoots(Color color){
        setAmount(1);
        setType(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) getItemMeta();
        meta.setColor(color);
        setItemMeta(meta);
    }
}
