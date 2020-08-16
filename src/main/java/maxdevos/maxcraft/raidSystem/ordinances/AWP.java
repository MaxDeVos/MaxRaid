package maxdevos.maxcraft.raidSystem.ordinances;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings({"deprecation", "unused"})
public class AWP {

    ItemStack i;

    public AWP(int allowedKills){
        i = new ItemStack(Material.BOW);
        i.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,32);
        i.addUnsafeEnchantment(Enchantment.ARROW_FIRE,32);
        i.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,32);
        i.addUnsafeEnchantment(Enchantment.ARROW_INFINITE,1);
        i.setAmount(1);
        i.setDurability((short)	(384-allowedKills));
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName("AWP");
        i.setItemMeta(meta);
    }

    public ItemStack getItem(){
        return i;
    }

}
