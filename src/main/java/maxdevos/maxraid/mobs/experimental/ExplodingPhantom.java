package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.PhantomChaseTarget;
import maxdevos.maxraid.goals.targets.NearestEntityOfClassGoal;
import maxdevos.maxraid.mobs.base.RaidPhantom;
import maxdevos.maxraid.raid.MaxRaid;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.world.entity.player.Player;
import org.bukkit.util.BlockVector;

public class ExplodingPhantom extends RaidPhantom {

    public ExplodingPhantom(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid, loc, new NMSSuicidePhantom(maxRaid));
        setCustomName(ChatColor.DARK_RED + "SUICIDE PHANTOM");
    }

    private static class NMSSuicidePhantom extends NMSPhantom {

        public NMSSuicidePhantom(MaxRaid raid) {
            super(raid);
        }

        @Override
        protected void registerRaidGoals() {
            goalSelector.addGoal(1, new PhantomChaseTarget(this, 1f));
            targetSelector.addGoal(1, new NearestEntityOfClassGoal<>(this, Player.class, 128));
        }

        @Override
        public void handleCollision(boolean horizontal){
            raid.getHandle().getLevel().getWorld().createExplosion(position().x, position().y + 1, position().z, 5f, true);
            kill();
        }
    }

}
