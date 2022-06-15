package maxdevos.maxraid.mobs.nmsMobs;

import maxdevos.maxraid.raid.NMSRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

public abstract class RaidMob extends Mob {
    NMSRaid raid;
    protected RaidMob(EntityType<? extends Mob> entitytypes, NMSRaid raid) {
        super(entitytypes, raid.serverLevel);
        raid.raidMobs.add(this);
        this.raid = raid;
    }
}
