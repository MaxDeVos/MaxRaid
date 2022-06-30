package maxdevos.maxraid.items.weapons.swords;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;

public class Level3Sword extends RaidSword {

    public Level3Sword() {
        super(RaidItemType.WeaponMaterial.DIAMOND);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
        this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
        this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    }
}
