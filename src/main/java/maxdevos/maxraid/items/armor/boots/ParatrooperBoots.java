package maxdevos.maxraid.items.armor.boots;

import maxdevos.maxraid.items.RaidItemType;
import org.bukkit.enchantments.Enchantment;

public class ParatrooperBoots extends RaidBoots {
    public ParatrooperBoots(RaidItemType.ArmorMaterial material) {
        super(material);
        addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 32);
    }
}
