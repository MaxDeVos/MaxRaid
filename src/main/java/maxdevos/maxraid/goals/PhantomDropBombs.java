package maxdevos.maxraid.goals;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.items.weapons.projecticles.TNTBomb;
import maxdevos.maxraid.mobs.experimental.BomberPhantom;
import maxdevos.maxraid.raid.RaidBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.block.TntBlock;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftMob;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

public class PhantomDropBombs extends Goal {

    Phantom mob;
    int cooldown = 10;

    public PhantomDropBombs(Phantom mob){
        this.mob = mob;
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
            cooldown = 0;
            mob.getLevel().addFreshEntity(new TNTBomb((CraftMob) mob.getBukkitEntity(), mob.getBukkitEntity().getLocation()).getHandle());
        }
        else{
            cooldown++;
        }
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void start() {
        cooldown = 0;
    }

    public void stop() {
        cooldown = 0;
    }

}
