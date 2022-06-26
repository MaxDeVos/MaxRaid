package maxdevos.maxraid.goals;

import maxdevos.maxraid.items.weapons.projecticles.TNTBomb;
import maxdevos.maxraid.mobs.experimental.BomberPhantom;
import maxdevos.maxraid.mobs.fleets.ParatrooperFleet;
import maxdevos.maxraid.raid.RaidBase;
import maxdevos.maxraid.util.VecTools;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMob;

public class DropParatroopers extends Goal {

    Phantom mob;
    int cooldown = 0;
    ParatrooperFleet fleet;

    public DropParatroopers(Phantom mob, ParatrooperFleet fleet){
        this.mob = mob;
        this.fleet = fleet;
    }


    @Override
    public boolean canUse() {
        return RaidBase.isLocationInBaseXZ(mob.getBukkitEntity().getLocation(), BomberPhantom.maxRaid.raidBase);
    }

//    @Override
//    public boolean canContinueToUse(){
//        super.canContinueToUse();
//    }


    @Override
    public void tick() {
        if(cooldown > 10){
            fleet.mobs.get(0).spawn(VecTools.vec3ToBlockVector(mob.position().add(new Vec3(Math.random() * 10, 0, Math.random() * 10))));
            fleet.mobs.remove(0);
        }
        else{
            cooldown++;
        }
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void start(){
        cooldown = 0;
    }
    public void stop(){
        cooldown = 0;
    }

}
