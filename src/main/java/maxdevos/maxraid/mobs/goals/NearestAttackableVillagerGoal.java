package maxdevos.maxraid.mobs.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.Villager;

public class NearestAttackableVillagerGoal extends NearestAttackableTargetGoal<Villager> {

    public NearestAttackableVillagerGoal(Mob entityinsentient) {
        super(entityinsentient, Villager.class, true);
    }

}
