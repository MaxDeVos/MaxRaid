package maxdevos.maxraid.items.weapons.axes;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;

public class Level2Axe extends RaidAxe {

    public Level2Axe() {
        super(RaidItemType.WeaponMaterial.DIAMOND);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    }
}
