package maxdevos.maxraid.base;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.BlockVector;

import java.awt.*;
import java.util.Random;

public class SpawnZone {

    int x1;
    int z1;
    int x2;
    int z2;
    Random random;
    public SpawnZone(int x1, int z1, int x2, int z2){
        random = new Random();
        this.x2 = Math.max(x1,x2);
        this.z2 = Math.max(z1,z2);
        this.x1 = Math.min(x1,x2);
        this.z1 = Math.min(z1,z2);
    }

    public BlockVector getRandomSpawn(){
        int x = random.nextInt(x2-x1) + x1;
        int z = random.nextInt(z2-z1) + z1;
        BlockVector out = new BlockVector(x, 0, z);
        System.out.println(out);
        return out;
    }

    public BlockVector getSpawnOnXAxis(int x){
        int z = random.nextInt(z2-z1) + z1;
        return new BlockVector(x, 0, z);
    }

    public BlockVector getSpawnOnZAxis(int z){
        int x = random.nextInt(x2-x1) + x1;
        return new BlockVector(x, 0, z);
    }

}
