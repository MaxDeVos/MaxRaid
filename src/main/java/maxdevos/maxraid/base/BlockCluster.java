package maxdevos.maxraid.base;

import maxdevos.maxraid.util.WorldUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BlockCluster<T extends Block> extends LinkedList<T> {

    Queue<Location> corners;
    public BlockCluster(){
        corners = new LinkedList<>();
    }


    private LinkedList<Coordinate> getUpdatedCoordinates(){
        removeIf(block -> !WorldUtils.isBlockType(block.getWorld().getBlockAt(block.getLocation()), "minecraft:sweet_berry_bush"));

        LinkedList<Coordinate> coordinates = new LinkedList<>();
        for(Block b:this){
            coordinates.add(new Coordinate(b.getX(), b.getZ()));
        }
        return coordinates;
    }

    public void calculateCorners(){

        corners.clear();
        Geometry convex = new ConvexHull(getUpdatedCoordinates().toArray(Coordinate[]::new), new GeometryFactory()).getConvexHull();
        for(Coordinate c:convex.getCoordinates()){
            int y = get(0).getWorld().getHighestBlockYAt(new Location(get(0).getWorld(),c.x,0,c.y)) + 1;
            corners.add(new Location(get(0).getWorld(), c.x, y, c.y));
        }
    }

    public Location getNextCorner(){
        if(corners.peek() != null){
            return corners.poll();
        }

        calculateCorners();
        if(corners.peek() != null) {
            return corners.poll();
        }
        return null;
    }
}
