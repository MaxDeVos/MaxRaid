package maxdevos.maxraid.mobs.experimental;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NMSCreeper extends Creeper {
    public NMSCreeper(Level world) {
        super(EntityType.CREEPER, world);
        this.setPos(new Vec3(world.levelData.getXSpawn(), world.levelData.getYSpawn(), world.levelData.getZSpawn()));
        this.goalSelector.removeAllGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1F,  25F));
    }
}
