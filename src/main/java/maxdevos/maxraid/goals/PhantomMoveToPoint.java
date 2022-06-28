package maxdevos.maxraid.goals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Phantom;

public class PhantomMoveToPoint extends Goal {

    Phantom mob;

    public PhantomMoveToPoint(Phantom mob){
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void start(){
        mob.getMoveControl().setWantedPosition(mob.getMoveControl().getWantedX(), mob.getMoveControl().getWantedY(), mob.getMoveControl().getWantedZ(), 1);
    }
}