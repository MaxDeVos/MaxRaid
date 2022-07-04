package maxdevos.maxraid.goals;

import maxdevos.maxraid.mobs.base.NMSPhantom;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Phantom;
import org.bukkit.util.BlockVector;

public class PhantomMoveToPoint extends Goal {

    NMSPhantom mob;
    private float speed;
    BlockVector dest;

    public PhantomMoveToPoint(NMSPhantom mob, BlockVector dest){
        this(mob, dest,1.0f);

    }

    public PhantomMoveToPoint(NMSPhantom mob, BlockVector dest, float speed){
        this.mob = mob;
        this.speed = speed;
        this.dest = dest;
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
