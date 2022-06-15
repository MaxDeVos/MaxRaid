package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.raid.NMSRaid;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;

public abstract class RaidMonster extends Monster {

    NMSRaid raid;

    protected RaidMonster(EntityType<? extends Monster> var0, NMSRaid raid) {
        super(var0, raid.serverLevel);
        raid.addMob(this);
        this.raid = raid;
    }

    @Override
    public void die(DamageSource damagesource) {
        super.die(damagesource);
        raid.updateBossbar();
        raid.raidMobs.remove(this);
    }
}
