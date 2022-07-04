package maxdevos.maxraid.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Location;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import java.util.Random;

public class VecUtil {

    private static Random random = new Random();

    public static BlockPos vec3ToBPos(Vec3 vec3){
        return new BlockPos(vec3.x, vec3.y, vec3.z);
    }

    public static BlockPos bVecToBPos(BlockVector blockVector){
        return new BlockPos(blockVector.getX(), blockVector.getY(), blockVector.getZ());
    }

    public static Vec3 bPosToVec3(BlockPos blockPos){
        return new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static Vec3 bVecToVec3(BlockVector blockVector){
        return new Vec3(blockVector.getX(), blockVector.getY(), blockVector.getZ());
    }

    public static Vec3 locToVec3(Location location){
        return new Vec3(location.getX(), location.getY(), location.getZ());
    }

    public static BlockVector locToBVec(Location location){
        return new BlockVector(location.getX(), location.getY(), location.getZ());
    }

    public static BlockPos locToBPos(Location location){
        return new BlockPos(location.getX(), location.getY(), location.getZ());
    }


    public static BlockVector bPosToBVec(BlockPos blockPos){
        return new BlockVector(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static BlockVector vec3ToBVec(Vec3 vec3){
        return new BlockVector(vec3.x, vec3.y, vec3.z);
    }

    public static Vector randomVector(int range){
        return new Vector(random.nextInt(range), 0, random.nextInt(range));
    }


    public static Vector randomVector(int xRange, int yRange, int zRange){
        return new Vector(random.nextInt(xRange), random.nextInt(yRange), random.nextInt(zRange));
    }

    public static Vector randomVector(int min, int max){
        return new Vector(min + random.nextInt(max-min), 0, min + random.nextInt(max-min));
    }





}
