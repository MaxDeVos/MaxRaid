package maxdevos.maxcraft.NMS;

import net.minecraft.server.v1_16_R1.EntityCreeper;
import net.minecraft.server.v1_16_R1.EntityTypes;
import net.minecraft.server.v1_16_R1.World;

public class CustomEntityCreeper extends EntityCreeper {

    public CustomEntityCreeper(World world) {
        super(EntityTypes.CREEPER, world);
    }

    protected void initAttributes(){

    }

}
