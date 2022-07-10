package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.GrazeGoal;
import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.VecUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;

public class NMSCow extends Cow implements RaidMob {
    MaxRaid raid;

    public NMSCow(MaxRaid raid) {
        super(EntityType.COW, raid.getHandle().serverLevel);
        this.raid = raid;
    }

    public void registerRaidGoals() {
    }
}