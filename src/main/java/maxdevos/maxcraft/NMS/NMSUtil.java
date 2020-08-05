package maxdevos.maxcraft.NMS;

import net.minecraft.server.v1_16_R1.EntityInsentient;
import net.minecraft.server.v1_16_R1.EntityTypes;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NMSUtil {

    public void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        try{
            List<Map<?,?>> datamap = new ArrayList<Map<?,?>>();
            for(Field f : EntityTypes.class.getDeclaredFields()){
                if(f.getType().getSimpleName().equals(Map.class.getSimpleName())){
                    f.setAccessible(true);
                    datamap.add((Map<?,?>) f.get(null));
                }
            }

            if(datamap.get(2).containsKey(id)){

                datamap.get(0).remove(name);
                datamap.get(2).remove(id);

            }

            Method method = EntityType.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null,customClass, name, id);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
