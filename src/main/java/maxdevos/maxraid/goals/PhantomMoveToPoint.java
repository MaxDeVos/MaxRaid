package maxdevos.maxraid.goals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Phantom;

public class PhantomMoveToPoint extends Goal {

    Phantom mob;
    private float speed;

    public PhantomMoveToPoint(Phantom mob){
        this(mob, 1.0f);
    }

    public PhantomMoveToPoint(Phantom mob, float speed){
        this.mob = mob;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void start(){
        mob.getMoveControl().setWantedPosition(mob.getMoveControl().getWantedX(), mob.getMoveControl().getWantedY(), mob.getMoveControl().getWantedZ(), speed);
    }
}
