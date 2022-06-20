package maxdevos.maxraid.items.weapons.swords;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;

public class Excalibur extends RaidSword {

    public Excalibur(){
        super(RaidItemType.WeaponMaterial.DIAMOND);
        meta.setDisplayName("Excalibur");
        addUnsafeEnchantment(Enchantment.DAMAGE_ALL,32);
        addUnsafeEnchantment(Enchantment.FIRE_ASPECT,32);
        addUnsafeEnchantment(Enchantment.SWEEPING_EDGE,32);
    }

}
