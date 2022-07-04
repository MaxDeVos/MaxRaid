package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.items.Equipper;
import maxdevos.maxraid.items.armor.helmets.BaseballHelmet;
import maxdevos.maxraid.items.weapons.swords.BaseballBat;
import maxdevos.maxraid.mobs.temp.RaidZombie;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.ChatColor;
import org.bukkit.util.BlockVector;

public class BabeRuth extends RaidZombie {

    public BabeRuth(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid, loc);
        setCustomName(ChatColor.BLUE + "Babe Ruth");
        getHandle().getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("raid bonus", 200f, AttributeModifier.Operation.ADDITION));
        getHandle().setHealth(200f);
        Equipper.setMobArmor(this, null);
        getEquipment().setHelmet(new BaseballHelmet());
        Equipper.setMobWeapon(this, new BaseballBat());
    }
}
