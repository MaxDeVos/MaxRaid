package maxdevos.maxraid.raid;

import org.bukkit.Location;
import org.bukkit.util.BlockVector;

public class RaidBase {
    public BlockVector dimensions;
    public BlockVector zeroCorner;
    public BlockVector center;

    public int topHeight;

    public RaidBase(BlockVector corner1, BlockVector corner2){
        double x = Math.abs(corner1.getX() - corner2.getX());
        double y = Math.abs(corner1.getY() - corner2.getY());
        double z = Math.abs(corner1.getZ() - corner2.getZ());
        dimensions = new BlockVector(x, y, z);

        double minX = Math.min(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        zeroCorner = new BlockVector(minX, minY, minZ);
        topHeight = (int) Math.ceil(minY + y);

        double centerX = minX + (x/2.0);
        double centerY = minY + (y/2.0);
        double centerZ = minZ + (z/2.0);
        center = new BlockVector(centerX, centerY, centerZ);
    }

    public static boolean isLocationInBaseXZ(Location location, RaidBase base){
        if(Math.abs(location.getX() - base.center.getX()) < base.dimensions.getX()/ 2.0){
            return Math.abs(location.getZ() - base.center.getZ()) < base.dimensions.getZ() / 2.0;
        }
        return false;

    }

}
