package maxdevos.maxraid.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.bukkit.util.BlockVector;

public class VecTools {

    public static BlockPos vec3ToBlockPos(Vec3 vec3){
        return new BlockPos(vec3.x, vec3.y, vec3.z);
    }

    public static BlockPos blockVectorToBlockPos(BlockVector blockVector){
        return new BlockPos(blockVector.getX(), blockVector.getY(), blockVector.getZ());
    }

    public static Vec3 blockPosToVec3(BlockPos blockPos){
        return new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static Vec3 blockVectorToVec3(BlockVector blockVector){
        return new Vec3(blockVector.getX(), blockVector.getY(), blockVector.getZ());
    }

    public static BlockVector blockPosToBlockVector(BlockPos blockPos){
        return new BlockVector(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static BlockVector vec3ToBlockVector(Vec3 vec3){
        return new BlockVector(vec3.x, vec3.y, vec3.z);
    }



}
