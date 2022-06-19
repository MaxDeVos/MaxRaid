package maxdevos.maxraid.goals;

import maxdevos.maxraid.RaidPlugin;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class DropBombs extends Goal {

    Mob mob;
    int cooldown;

    public DropBombs(Mob mob){
        this.mob = mob;
    }


    @Override
    public boolean canUse() {
        if(mob.getTarget() != null){
            double dX = mob.getPosition(0f).x - mob.getTarget().getPosition(0.0f).x;
            double dY = mob.getPosition(0f).y - mob.getTarget().getPosition(0.0f).y;
            double dZ = mob.getPosition(0f).z - mob.getTarget().getPosition(0.0f).z;

            // In range laterally
            boolean inRangeX = Math.abs(dX) < 2;
            boolean inRangeZ = Math.abs(dZ) < 2;

            // High enough above target
            boolean inRangeY = dY > 5;

            return inRangeX && inRangeY && inRangeZ;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse(){
        return cooldown > 40;
    }

    @Override
    public void tick() {
        cooldown++;
    }

    @Override
    public void start() {
        cooldown = 0;
        RaidPlugin.getServerInstance().broadcastMessage("DROP BOMB");
    }

}
