package maxdevos.maxraid.items.weapons.bows;

import org.bukkit.enchantments.Enchantment;

public class AWP extends RaidBow {

    public AWP(){
        super();
        meta.setDisplayName("AWP");
        addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,32);
        addUnsafeEnchantment(Enchantment.ARROW_FIRE,32);
        addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,32);
        addUnsafeEnchantment(Enchantment.ARROW_INFINITE,1);
    }

}
