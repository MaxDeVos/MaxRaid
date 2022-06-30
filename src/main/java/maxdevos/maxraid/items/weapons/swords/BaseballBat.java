package maxdevos.maxraid.items.weapons.swords;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

public class BaseballBat extends RaidSword {

    public BaseballBat() {
        super(RaidItemType.WeaponMaterial.WOOD);
        ItemMeta meta = getItemMeta();
        meta.setUnbreakable(true);
        this.setItemMeta(meta);
        this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 32);
    }
}
