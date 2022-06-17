package maxdevos.maxraid.items.weapons.swords;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RaidSword extends ItemStack {

    public RaidSword(RaidItemType.WeaponMaterial material){
        setAmount(1);
        switch (material){
            case WOOD -> setType(Material.WOODEN_SWORD);
            case STONE -> setType(Material.STONE_SWORD);
            case GOLD -> setType(Material.GOLDEN_SWORD);
            case IRON -> setType(Material.IRON_SWORD);
            case DIAMOND -> setType(Material.DIAMOND_SWORD);
            case NETHERITE -> setType(Material.NETHERITE_SWORD);
        }
    }

}
