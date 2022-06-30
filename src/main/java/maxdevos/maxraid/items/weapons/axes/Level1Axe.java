package maxdevos.maxraid.items.weapons.axes;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;

public class Level1Axe extends RaidAxe {

    public Level1Axe() {
        super(RaidItemType.WeaponMaterial.IRON);
        this.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        this.addEnchantment(Enchantment.KNOCKBACK, 1);
    }
}
