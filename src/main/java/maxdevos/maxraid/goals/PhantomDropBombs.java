package maxdevos.maxraid.goals;

import maxdevos.maxraid.items.weapons.projecticles.TNTBomb;
import maxdevos.maxraid.mobs.base.NMSPhantom;
import maxdevos.maxraid.mobs.experimental.BomberPhantom;
import maxdevos.maxraid.raid.RaidBase;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Phantom;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMob;

import java.util.Random;

public class PhantomDropBombs extends Goal {

    NMSPhantom mob;
    double averageBombsPerSecond = 1;

    public PhantomDropBombs(NMSPhantom mob, double averageBombsPerSecond){
        this.mob = mob;
        this.averageBombsPerSecond = averageBombsPerSecond;
    }


    @Override
    public boolean canUse() {
        return RaidBase.isLocationInBaseXZ(mob.getBukkitEntity().getLocation(), mob.raid.raidBase);
    }

    @Override
    public void tick() {
        if(new Random().nextDouble(20) <= averageBombsPerSecond){
            mob.getLevel().addFreshEntity(new TNTBomb((CraftMob) mob.getBukkitEntity(), mob.getBukkitEntity().getLocation()).getHandle());
            mob.getLevel().getWorld().playSound(mob.getBukkitEntity().getLocation(), Sound.ENTITY_TNT_PRIMED, 2f, 1f);
        }
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

}
