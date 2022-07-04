package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.PhantomDropBombs;
import maxdevos.maxraid.goals.PhantomMoveToPoint;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.mobs.base.NMSPhantom;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPhantom;
import org.bukkit.util.BlockVector;

public class BomberPhantom extends NMSPhantom {
    double bombsPerSecond;
    BlockVector dest;
    public BomberPhantom(MaxRaid raid, BlockVector dest, double bombsPerSecond) {
        super(raid, 0.5f, 5);
        this.bombsPerSecond = bombsPerSecond;
        this.dest = dest;
    }

    @Override
    public void registerRaidGoals() {
        goalSelector.addGoal(1, new PhantomDropBombs(this, bombsPerSecond));
        goalSelector.addGoal(2, new PhantomMoveToPoint(this, dest));
    }

}
