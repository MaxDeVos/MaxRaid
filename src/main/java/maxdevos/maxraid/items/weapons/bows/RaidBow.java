package maxdevos.maxraid.items.weapons.bows;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RaidBow extends ItemStack {

    protected ItemMeta meta;
    public RaidBow(){
        setAmount(1);
        setType(Material.BOW);
        meta = getItemMeta();
        meta.setDisplayName("Raid Bow");
    }
}
