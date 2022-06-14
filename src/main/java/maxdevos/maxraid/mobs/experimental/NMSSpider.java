package maxdevos.maxraid.mobs.experimental;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NMSSpider extends Spider {
    public NMSSpider(Level world) {
        super(EntityType.SPIDER, world);
        this.setPos(new Vec3(world.levelData.getXSpawn(), world.levelData.getYSpawn(), world.levelData.getZSpawn()));
        this.goalSelector.removeAllGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Creeper.class, 20.0F, 1.0, 1.2));
    }
}
