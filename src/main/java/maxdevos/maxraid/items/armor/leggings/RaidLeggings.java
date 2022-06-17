package maxdevos.maxraid.items.armor.leggings;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RaidLeggings extends ItemStack {

    public RaidLeggings(RaidItemType.ArmorMaterial material){
        setAmount(1);
        switch (material){
            case LEATHER -> setType(Material.LEATHER_LEGGINGS);
            case GOLD -> setType(Material.GOLDEN_LEGGINGS);
            case CHAINMAIL -> setType(Material.CHAINMAIL_LEGGINGS);
            case IRON -> setType(Material.IRON_LEGGINGS);
            case DIAMOND -> setType(Material.DIAMOND_LEGGINGS);
            case NETHERITE -> setType(Material.NETHERITE_LEGGINGS);
        }
    }
}
