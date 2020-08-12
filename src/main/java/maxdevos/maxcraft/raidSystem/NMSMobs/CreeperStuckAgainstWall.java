package maxdevos.maxcraft.raidSystem.NMSMobs;

import maxdevos.maxcraft.MaxPlugin;
import maxdevos.maxcraft.util.ChatFunctions;
import net.minecraft.server.v1_16_R1.EntityCreeper;
import net.minecraft.server.v1_16_R1.EntityLiving;
import net.minecraft.server.v1_16_R1.PathfinderGoal;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;

import java.util.EnumSet;
import java.util.Set;

public class CreeperStuckAgainstWall extends PathfinderGoal {

    EntityCreeper creeper;
    private EntityLiving target;
    private double x;
    private double y;
    private double z;
    private int i = 0;
    Block b;
    Block oldBlock;

    public CreeperStuckAgainstWall(EntityCreeper e){
        this.a(EnumSet.of(Type.MOVE));
        this.creeper = e;
    }

    @Override
    public boolean a() {
        this.target = this.creeper.getGoalTarget();
        if(this.target == null){
            return false;
        }
        this.x = this.target.locX();
        this.y = this.target.locY();
        this.z = this.target.locZ();
        return true;
    }

    public void c(){
        creeper.getNavigation().a(this.x,this.y,this.z,2.0);
        b = ((Creeper) creeper.getBukkitEntity()).getTargetBlock((Set<Material>) null, 10);
        if (b.equals(oldBlock)) {
            i++;
        }
        else{
            oldBlock = ((Creeper) creeper.getBukkitEntity()).getTargetBlock((Set<Material>) null, 10);
            i = 0;
        }
//        System.out.println(i);

        if(i > 20){
            creeper.explode();
        }

    }

    public boolean b(){
        return false;
    }

}
