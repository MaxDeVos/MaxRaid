package maxdevos.maxraid.items.weapons.axes;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;

public class Level3Axe extends RaidAxe {

    public Level3Axe() {
        super(RaidItemType.WeaponMaterial.NETHERITE);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
        this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
        this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    }
}
