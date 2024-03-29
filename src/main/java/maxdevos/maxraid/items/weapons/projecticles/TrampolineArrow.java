package maxdevos.maxraid.items.weapons.projecticles;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;

public class TrampolineArrow extends BaseArrow {

    public TrampolineArrow(CraftEntity archer) {
        super(archer, new NMSTrampolineArrow(archer));
    }

    private static class NMSTrampolineArrow extends NMSArrowBase {
        public NMSTrampolineArrow(CraftEntity archer) {
            super(archer);
        }

        @Override
        protected void doPostHurtEffects(LivingEntity entityliving) {
            super.doPostHurtEffects(entityliving);
            entityliving.setDeltaMovement(new Vec3(0, 5, 0));
        }
    }
}
