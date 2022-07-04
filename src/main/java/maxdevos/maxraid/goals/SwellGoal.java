package maxdevos.maxraid.goals;

import java.util.EnumSet;
import javax.annotation.Nullable;

import maxdevos.maxraid.mobs.base.NMSCreeper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class SwellGoal extends Goal {
    private final NMSCreeper creeper;
    @Nullable
    private LivingEntity target;

    public SwellGoal(NMSCreeper var0) {
        this.creeper = var0;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean canUse() {
        LivingEntity var0 = this.creeper.getTarget();
        return this.creeper.getSwellDir() > 0 || var0 != null && this.creeper.distanceToSqr(var0) < 9.0;
    }

    public void start() {
        this.creeper.getNavigation().stop();
        this.target = this.creeper.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.target == null) {
            this.creeper.setSwellDir(-1);
        } else if (this.creeper.distanceToSqr(this.target) > 49.0) {
            this.creeper.setSwellDir(-1);
        } else if (!this.creeper.getSensing().hasLineOfSight(this.target)) {
            this.creeper.setSwellDir(-1);
        } else {
            this.creeper.setSwellDir(1);
        }
    }
}
