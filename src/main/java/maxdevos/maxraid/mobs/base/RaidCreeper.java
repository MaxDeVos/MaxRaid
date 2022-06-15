package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.NMSRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;

public class RaidCreeper extends Creeper {
    NMSRaid raid;

    public RaidCreeper(EntityType<? extends Creeper> entitytypes, Level world) {
        super(entitytypes, world);
    }


    @Override
    public void die(DamageSource damagesource) {
        super.die(damagesource);
        raid.raidMobs.remove(this);
        raid.updateBossbar();
    }

    public boolean hurt(DamageSource damagesource, float f) {
        raid.updateBossbar();
        return super.hurt(damagesource, f);
    }
}
