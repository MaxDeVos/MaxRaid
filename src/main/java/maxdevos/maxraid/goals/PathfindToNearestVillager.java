package maxdevos.maxraid.goals;

import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.VecTools;
import net.minecraft.world.entity.monster.Creeper;

public class PathfindToNearestVillager extends PathfindToRaidWall {

    Creeper creeper;
    boolean isExploding = false;
    int explodingTimer = 0;

    public PathfindToNearestVillager(Creeper m, MaxRaid raid) {
        super(m, raid);
        this.creeper = m;
    }

    public void tick(){
        super.tick();
        if(creeper.position().distanceTo(VecTools.blockPosToVec3(goalPos)) < 1.0){
            creeper.setPos(creeper.position().add(0,4,0));
            creeper.explodeCreeper();
        }
        if(isExploding){
            explodingTimer++;
            if(explodingTimer > 10){

            }
        }
    }

    public void doStuck(){
        if(creeper.position().distanceTo(VecTools.blockPosToVec3(goalPos)) < 3.0){
            creeper.setPos(creeper.position().add(0,4,0));
            creeper.explodeCreeper();
        } else{
            creeper.setDeltaMovement(Math.random() * 4, 3, Math.random() * 4);
        }
//        creeper.explodeCreeper();
    }
}
