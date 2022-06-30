package maxdevos.maxraid.goals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Phantom;

public class PhantomChaseTarget extends Goal {

    Phantom mob;
    float speedModifier;

    public PhantomChaseTarget(Phantom mob, float speedModifier){
        this.mob = mob;
        this.speedModifier = speedModifier;
    }

    @Override
    public boolean canUse() {
        System.out.println(mob.getTarget());
        return mob.getTarget() != null;
    }

    @Override
    public void tick(){
        if(mob.getTarget() != null){
            mob.getMoveControl().setWantedPosition(mob.getTarget().position().x, mob.getTarget().position().y, mob.getTarget().position().z, speedModifier);
        }
    }
}
