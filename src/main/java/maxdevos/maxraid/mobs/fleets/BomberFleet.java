package maxdevos.maxraid.mobs.fleets;

import maxdevos.maxraid.mobs.experimental.BomberPhantom;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.util.BlockVector;

import java.util.LinkedList;

public class BomberFleet {

    MaxRaid raid;
    public int bombTickInterval = 20;
    LinkedList<BomberPhantom> phantoms;

    public BomberFleet(MaxRaid raid, double spacing, double bombsPerSecond){
        phantoms = new LinkedList<>();

        double[] phantomZPos = {568, 579, 588, 593, 598, 609};

        double phantomX = raid.raidBase.zeroCorner.getX() - 100;
        double phantomGoalX = raid.raidBase.zeroCorner.getX() + raid.raidBase.dimensions.getX() + 60;
        double phantomY = raid.raidBase.topHeight + 5;
        int numPhantoms = (int) Math.floor(raid.raidBase.dimensions.getZ() - 6.0 / spacing);
        double phantomZBase = raid.raidBase.zeroCorner.getZ() + 3;

        for(int i = 0; i < phantomZPos.length; i++){
            double phantomZ = phantomZBase + i * spacing;
            BlockVector spawnPoint = new BlockVector(phantomX, phantomY, phantomZPos[i]);
            BlockVector goalPoint = new BlockVector(phantomGoalX, phantomY, phantomZPos[i]);
            new BomberPhantom(raid, spawnPoint, goalPoint, bombsPerSecond);
        }
    }
}
