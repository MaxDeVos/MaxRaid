package maxdevos.maxraid.goals;

import maxdevos.maxraid.base.RaidBase;
import net.minecraft.world.entity.ai.goal.Goal;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftCreeper;
import org.bukkit.util.Vector;

public class CreeperExplodeAgainstWallGoal extends Goal {

    CraftCreeper mob;

    public CreeperExplodeAgainstWallGoal(CraftCreeper creeper){
        mob = creeper;
    }

    @Override
    public boolean canUse() {

        Block eyeHeightBlock = mob.getWorld().getBlockAt(mob.getEyeLocation().add(mob.getFacing().getDirection()));
        Block oneAboveEyeHeightBlock = mob.getWorld().getBlockAt(mob.getEyeLocation().add(mob.getFacing().getDirection()).add(new Vector(0, 1, 0)));
        boolean eyeHeightBlockValid = !eyeHeightBlock.isEmpty() && !RaidBase.ignoredMaterials.contains(eyeHeightBlock.getType());
        boolean oneAboveEyeHeightBlockValid = !oneAboveEyeHeightBlock.isEmpty() && !RaidBase.ignoredMaterials.contains(oneAboveEyeHeightBlock.getType());
        return eyeHeightBlockValid && oneAboveEyeHeightBlockValid && !mob.isInWater();
    }

    @Override
    public void start(){

        Block eyeHeightBlock = mob.getWorld().getBlockAt(mob.getEyeLocation().add(mob.getFacing().getDirection()));
        Block oneAboveEyeHeightBlock = mob.getWorld().getBlockAt(mob.getEyeLocation().add(mob.getFacing().getDirection()).add(new Vector(0, 1, 0)));
        System.out.println("FOUND " + eyeHeightBlock.getType() + " AND " + oneAboveEyeHeightBlock.getType() + ".  EXPLODING");

        mob.explode();
    }
}
