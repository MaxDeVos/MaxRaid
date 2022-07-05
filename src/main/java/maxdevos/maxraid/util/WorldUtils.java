package maxdevos.maxraid.util;

import maxdevos.maxraid.base.BlockCluster;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WorldUtils {

    public static boolean flag = true;

    public static List<BlockCluster<Block>> clusterizeAll(List<Block> originalBlocks){

        List<BlockCluster<Block>> clusters = new LinkedList<>();

        BlockCluster<Block> allBlocks = new BlockCluster<>();
        allBlocks.addAll(originalBlocks);

        while(!allBlocks.isEmpty()){
            BlockCluster<Block> latestCluster = clusterize(allBlocks.get(0), allBlocks);
            clusters.add(latestCluster);
            allBlocks.removeAll(latestCluster);
        }

        if(flag) {
            for (BlockCluster<Block> cluster : clusters) {
                cluster.calculateCorners();
            }
            flag = false;
        }

        return clusters;

    }

    public static BlockCluster<Block> clusterize(Block b, LinkedList<Block> allBlocks){

        BlockCluster<Block> cluster = new BlockCluster<>();
        allBlocks.remove(b);
        cluster.add(b);


        for(Block n:getSurfaceNeighbors(b)){
            if(allBlocks.contains(n) && !cluster.contains(n)){
                cluster.addAll(clusterize(n, allBlocks));
            }
        }

        cluster.calculateCorners();
        return cluster;

    }

    public static List<Block> getSurfaceNeighbors(Block b){
        LinkedList<Block> out = new LinkedList<>();
        int x = b.getLocation().getBlockX();
        int z = b.getLocation().getBlockZ();
        out.add(b.getWorld().getBlockAt(b.getWorld().getHighestBlockAt(new Location(b.getWorld(), x+1, 0, z)).getLocation().add(0,1,0)));
        out.add(b.getWorld().getBlockAt(b.getWorld().getHighestBlockAt(new Location(b.getWorld(), x-1, 0, z)).getLocation().add(0,1,0)));
        out.add(b.getWorld().getBlockAt(b.getWorld().getHighestBlockAt(new Location(b.getWorld(), x, 0, z+1)).getLocation().add(0,1,0)));
        out.add(b.getWorld().getBlockAt(b.getWorld().getHighestBlockAt(new Location(b.getWorld(), x, 0, z-1)).getLocation().add(0,1,0)));
        return out;
    }

    public static Location getLowestAirBlock(Location l){
        for(int i = l.getBlockY(); i < 256; i++){
            Location temp = new Location(l.getWorld(), l.getX(), l.getBlockY()+i, l.getZ());
            if(temp.getBlock().getType().equals(Material.AIR)){
                return temp;
            }
        }
        return new Location(l.getWorld(),l.getX(),256.0,l.getZ());
    }

    public static Location findReasonableLocation(Location l, int goalLegDist){
        Random r = new Random();
        l = l.subtract(0,3,0);
        Location inital = getLowestAirBlock(l.add(goalLegDist,-10,goalLegDist));
        if(inital.getY() - l.getY() < 3){
            return inital;
        }
        int x = -goalLegDist;
        int z = -goalLegDist;
        while (true){
            inital = getLowestAirBlock(new Location(l.getWorld(), l.getX() + x, l.getY(),l.getZ() + z));
            x++;
            z++;
            if(inital.getY() - l.getY() < 3 || x > goalLegDist){
                break;
            }
            else{
                x++;
                z++;
            }
        }
        return inital;
    }

    public static boolean isBlockType(Block b, String type){
        return b.getBlockData().getAsString().toLowerCase().split("\\[")[0].equals(type);
    }
}
