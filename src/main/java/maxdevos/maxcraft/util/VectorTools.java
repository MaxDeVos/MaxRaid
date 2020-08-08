package maxdevos.maxcraft.util;

import org.bukkit.util.Vector;

import java.util.Random;

@SuppressWarnings("unused")
public class VectorTools {

    private static Random random;

    public static Vector generateRandomVector(int range){
        random = new Random();
        return new Vector(random.nextInt(range), random.nextInt(range), random.nextInt(range));
    }


    public static Vector generateRandomVector(int xRange, int yRange, int zRange){
        random = new Random();
        return new Vector(random.nextInt(xRange), random.nextInt(yRange), random.nextInt(zRange));
    }

    public static Vector generateRandomVector(int min, int max){
        random = new Random();
        return new Vector(min + random.nextInt(max-min), 0, min + random.nextInt(max-min));
    }

}
