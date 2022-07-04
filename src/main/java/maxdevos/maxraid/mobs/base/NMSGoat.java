package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.GrazeGoal;
import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.bukkit.ChatColor;

public class NMSGoat extends Goat implements RaidMob {
    MaxRaid raid;

    public NMSGoat(MaxRaid raid) {
        super(EntityType.GOAT, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Goat");
        this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, 2F);
    }

    public void registerRaidGoals() {
        goalSelector.addGoal(1, new GrazeGoal(this));
    }
}