package maxdevos.maxraid.items.armor.chestplates;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RaidChestplate extends ItemStack {
    public RaidChestplate(RaidItemType.ArmorMaterial material){
        setAmount(1);
        switch (material){
            case LEATHER -> setType(Material.LEATHER_CHESTPLATE);
            case GOLD -> setType(Material.GOLDEN_CHESTPLATE);
            case CHAINMAIL -> setType(Material.CHAINMAIL_CHESTPLATE);
            case IRON -> setType(Material.IRON_CHESTPLATE);
            case DIAMOND -> setType(Material.DIAMOND_CHESTPLATE);
            case NETHERITE -> setType(Material.NETHERITE_CHESTPLATE);
        }
    }

    public RaidChestplate(Color color){
        setAmount(1);
        setType(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) getItemMeta();
        meta.setColor(color);
        setItemMeta(meta);
    }
}
