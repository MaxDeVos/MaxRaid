package maxdevos.maxraid.mobs.fleets;

import maxdevos.maxraid.mobs.experimental.BomberPhantom;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.util.BlockVector;

import java.util.LinkedList;

public class BomberFleet {

    MaxRaid raid;
    public int bombTickInterval = 20;
    LinkedList<BomberPhantom> phantoms;

    public BomberFleet(MaxRaid raid, double spacing){
        phantoms = new LinkedList<>();

        double phantomX = raid.raidBase.zeroCorner.getX() - 60;
        double phantomGoalX = raid.raidBase.zeroCorner.getX() + raid.raidBase.dimensions.getX() + 60;
        double phantomY = raid.raidBase.topHeight + 15;
        int numPhantoms = (int) Math.floor(raid.raidBase.dimensions.getZ() - 6.0 / spacing);
        double phantomZBase = raid.raidBase.zeroCorner.getZ() + 3;

        for(int i = 0; i < numPhantoms; i++){
            double phantomZ = phantomZBase + i * spacing;
            BlockVector spawnPoint = new BlockVector(phantomX, phantomY, phantomZ);
            BlockVector goalPoint = new BlockVector(phantomGoalX, phantomY, phantomZ);
            new BomberPhantom(raid, spawnPoint, goalPoint);
        }
    }
}
