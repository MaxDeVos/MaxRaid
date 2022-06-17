package maxdevos.maxraid.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.bukkit.util.BlockVector;

import java.util.EnumSet;

public class LookAtPointGoal extends Goal {
    private final PathfinderMob mob;
    private Vec3 goalPos;

    int lookTime;

    double probability;


    public LookAtPointGoal(PathfinderMob var0, BlockVector pos) {
        this(var0, pos, 1);
    }

    public LookAtPointGoal(PathfinderMob var0, BlockVector pos, double probability) {
        this.mob = var0;
        this.goalPos = new Vec3(pos.getX(), pos.getY(), pos.getZ());
        this.setFlags(EnumSet.of(Flag.LOOK));
        this.probability = probability;
        this.lookTime = 100;
    }


    public boolean canUse() {
        return this.mob.getRandom().nextFloat() < this.probability;
    }

    public boolean canContinueToUse() {
        return this.lookTime > 0;
    }

    public void start() {
        this.lookTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
    }

    public void tick() {
        this.mob.getLookControl().setLookAt(goalPos.x, this.mob.getEyeY(), goalPos.z);
        --this.lookTime;
    }

}
