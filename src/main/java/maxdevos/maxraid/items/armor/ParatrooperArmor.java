package maxdevos.maxraid.items.armor;

import maxdevos.maxraid.items.RaidItemType;
import maxdevos.maxraid.items.armor.boots.ParatrooperBoots;

public class ParatrooperArmor extends RaidArmor {

    public ParatrooperArmor(RaidItemType.ArmorMaterial material) {
        super(material);
        boots = new ParatrooperBoots(material);
    }

}
