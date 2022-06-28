package maxdevos.maxraid.items.armor.helmets;

import maxdevos.maxraid.items.RaidItemType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Set;

public class RaidHelmet extends ItemStack {

    public RaidHelmet(RaidItemType.ArmorMaterial material){
        setAmount(1);
        switch (material){
            case LEATHER -> setType(Material.LEATHER_HELMET);
            case GOLD -> setType(Material.GOLDEN_HELMET);
            case CHAINMAIL -> setType(Material.CHAINMAIL_HELMET);
            case IRON -> setType(Material.IRON_HELMET);
            case DIAMOND -> setType(Material.DIAMOND_HELMET);
            case NETHERITE -> setType(Material.NETHERITE_HELMET);
        }
    }

    public RaidHelmet(Color color){
        setAmount(1);
        setType(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) getItemMeta();
        meta.setColor(color);
    }

}
