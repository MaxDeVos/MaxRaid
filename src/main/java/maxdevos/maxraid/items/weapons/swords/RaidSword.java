package maxdevos.maxraid.items.weapons.swords;

import maxdevos.maxraid.items.RaidItemType;
import net.minecraft.world.item.SwordItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RaidSword extends ItemStack {

    protected ItemMeta meta;
    public RaidSword(RaidItemType.WeaponMaterial material){
        meta = getItemMeta();
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
