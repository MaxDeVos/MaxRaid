package maxdevos.maxraid.items.weapons.axes;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RaidAxe extends ItemStack {

    public RaidAxe(RaidItemType.WeaponMaterial material){
        setAmount(1);
        switch (material){
            case WOOD -> setType(Material.WOODEN_AXE);
            case STONE -> setType(Material.STONE_AXE);
            case GOLD -> setType(Material.GOLDEN_AXE);
            case IRON -> setType(Material.IRON_AXE);
            case DIAMOND -> setType(Material.DIAMOND_AXE);
            case NETHERITE -> setType(Material.NETHERITE_AXE);
        }
    }

}
