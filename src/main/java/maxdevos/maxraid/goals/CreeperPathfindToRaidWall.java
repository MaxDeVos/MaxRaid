package maxdevos.maxraid.goals;

import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.VecUtil;
import net.minecraft.world.entity.monster.Creeper;

public class CreeperPathfindToRaidWall extends PathfindToRaidWall {

    Creeper creeper;

    public CreeperPathfindToRaidWall(Creeper m, MaxRaid raid) {
        super(m, raid);
        this.creeper = m;
    }

    public void tick(){
        super.tick();
        if(creeper.position().distanceTo(VecUtil.bPosToVec3(goalPos)) < 1.0){
            creeper.setPos(creeper.position().add(0,4,0));
            creeper.explodeCreeper();
        }
    }

    public void doStuck(){
        if(creeper.position().distanceTo(VecUtil.bPosToVec3(goalPos)) < 3.0){
            creeper.setPos(creeper.position().add(0,4,0));
            creeper.explodeCreeper();
        } else{
            creeper.setDeltaMovement(Math.random() * 4, 3, Math.random() * 4);
        }
    }
}
