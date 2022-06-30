package maxdevos.maxraid.items.weapons.swords;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;

public class Level2Sword extends RaidSword {

    public Level2Sword() {
        super(RaidItemType.WeaponMaterial.DIAMOND);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
        this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    }
}
