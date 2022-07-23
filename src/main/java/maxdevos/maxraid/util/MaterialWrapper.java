package maxdevos.maxraid.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

/** https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html */
public class MaterialWrapper {

    public static void setBlockAsMaterial(Block b, String material){
        Material mat;
        if((mat = Material.getMaterial(material)) != null){
            b.setType(mat);
        }
        else{
            throw new IllegalStateException("NO SUCH MATERIAL: " + material);
        }
    }

}