package maxdevos.maxraid.util;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Random;

public class WorldUtils {

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

}
