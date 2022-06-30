package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.items.Equipper;
import maxdevos.maxraid.items.armor.helmets.BaseballHelmet;
import maxdevos.maxraid.items.weapons.swords.BaseballBat;
import maxdevos.maxraid.mobs.base.RaidZombie;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.ChatColor;
import org.bukkit.util.BlockVector;

public class BabeRuth extends RaidZombie {

    public BabeRuth(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid, loc);
        setCustomName(ChatColor.BLUE + "Babe Ruth");
        setMaxHealth(150);
        Equipper.setMobArmor(this, null);
        getEquipment().setHelmet(new BaseballHelmet());
        Equipper.setMobWeapon(this, new BaseballBat());
    }
}
