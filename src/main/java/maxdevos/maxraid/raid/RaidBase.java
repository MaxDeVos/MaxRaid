package maxdevos.maxraid.raid;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.BlockVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class RaidBase {
    public BlockVector dimensions;
    public BlockVector zeroCorner;
    public BlockVector center;
    private ServerLevel level;
    public int topHeight;
    public static ArrayList<AttackPoint> wallAttackPoints;

    private static final Material[] ignoredMaterialsArray = {Material.DIRT, Material.DIRT_PATH, Material.GRASS_BLOCK,
            Material.SAND, Material.COBBLESTONE, Material.STONE, Material.WATER};
    public static HashSet<Material> ignoredMaterials = new HashSet<>(Arrays.stream(ignoredMaterialsArray).toList());

    public RaidBase(ServerLevel level, BlockVector corner1, BlockVector corner2){
        double xDim = Math.abs(corner1.getX() - corner2.getX());
        double yDim = Math.abs(corner1.getY() - corner2.getY());
        double zDim = Math.abs(corner1.getZ() - corner2.getZ());
        dimensions = new BlockVector(xDim, yDim, zDim);

        double minX = Math.min(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        zeroCorner = new BlockVector(minX, minY, minZ);
        topHeight = (int) Math.ceil(minY + yDim);

        double centerX = minX + (xDim/2.0);
        double centerY = minY + (yDim/2.0);
        double centerZ = minZ + (zDim/2.0);
        center = new BlockVector(centerX, centerY, centerZ);

        this.level = level;

        // Raid Yard Calculations, expand search area by 20 blocks in each direction on X and Z axes
        minX -= 20;
        minZ -= 20;
        xDim += 40;
        zDim += 40;

        wallAttackPoints = new ArrayList<>();

        int[][] heightMap = new int[(int) Math.ceil(xDim)][(int) Math.ceil(zDim)];

        for(int x = (int) minX; x < minX + xDim; x++){
            for(int z = (int) minZ; z < minZ + zDim; z++){
                heightMap[(int) (x-minX)][(int) (z-minZ)] = level.getHeight(Heightmap.Types.MOTION_BLOCKING, x,z);
            }
        }

        for(int x = 1; x < xDim - 1; x++){
            for(int z = 1; z < zDim - 1; z++){
                int dY = calculateHighestYDeltaOfNeighbors(heightMap, x, z);
                if(dY > 4 && heightMap[x][z] > 90){
                    AttackPoint point = new AttackPoint((int) (x+minX), heightMap[x][z] - dY,heightMap[x][z],
                            (int) (z+minZ));

                    // Force a distance of 3 blocks between attack points to avoid swarming
                    // (disable if swarming is desired)
                    boolean isolated = true;
                    for(AttackPoint ap:wallAttackPoints){
                        if(point.withinRangeOfAttackPoint(ap, 3.0)){
                           isolated = false;
                           break;
                        }
                    }

                    if(isolated){
                        System.out.println("CREATED ATTACK POINT: " + point);
                        wallAttackPoints.add(point);
                    }
                }
            }
        }
    }

    public int calculateHighestYDeltaOfNeighbors(int[][] heightMap, int x, int z){
        int h = heightMap[x][z];
        ArrayList<Integer> deltas = new ArrayList<>();

        deltas.add(h - heightMap[x+1][z]);
        deltas.add(h - heightMap[x-1][z]);
        deltas.add(h - heightMap[x][z+1]);
        deltas.add(h - heightMap[x][z-1]);

        Collections.sort(deltas);
        Collections.reverse(deltas);

        return deltas.get(0);
    }

    public static boolean isLocationInBaseXZ(Location location, RaidBase base){
        if(Math.abs(location.getX() - base.center.getX()) < base.dimensions.getX()/ 2.0){
            return Math.abs(location.getZ() - base.center.getZ()) < base.dimensions.getZ() / 2.0;
        }
        return false;
    }

    public AttackPoint getNearestWallAttackPoint(BlockPos blockPos){
        AttackPoint closest = new AttackPoint(center.getBlockX(), center.getBlockY(), center.getBlockY(),
                center.getBlockZ());
        double minDistance = Double.MAX_VALUE;
        for(AttackPoint a:wallAttackPoints){
            if(!level.getWorld().getBlockAt(a.x, a.y_base, a.z).isEmpty()){
                double dist = Math.sqrt( Math.pow(blockPos.getX()-a.x,2) + Math.pow(blockPos.getZ()-a.z,2));
                if(minDistance > dist){
//                    System.out.println("NEW CLOSEST: " + a + " DIST: " + dist);
                    minDistance = dist;
                    closest = a;
                }
            }
        }
        return closest;
    }

}
