package maxdevos.maxraid.raid;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import org.bukkit.util.BlockVector;

public class AttackPoint {

    public int x;
    public int y_base;
    public int y_top;
    public int z;

    protected AttackPoint(int x, int y_base, int y_top, int z){
        this.x = x;
        this.y_base = y_base;
        this.y_top = y_top;
        this.z = z;
    }

    protected AttackPoint(){
        this(0,0,0,0);
    }

    public BlockVector getBaseBlockVector(){
        return new BlockVector(x, y_base, z);
    }

    public Vec3 getBaseVec3(){
        return new Vec3(x, y_base, z);
    }

    public BlockPos getBaseBlockPos(){
        return new BlockPos(x, y_base, z);
    }

    public boolean equals(Object o){
        if(o instanceof AttackPoint point){
            return (point.x == this.x && point.z == this.z);
        } else if(o instanceof BlockPos point){
            return (point.getX() == this.x && point.getZ() == this.z);
        } else if(o instanceof BlockVector point){
            return ((int) Math.round(point.getX()) == this.x && (int) Math.round(point.getZ()) == this.z);
        } else if(o instanceof Vec3 point){
            return ((int) Math.round(point.x) == this.x && (int) Math.round(point.x) == this.z);
        }
        return false;
    }


    public boolean withinRangeOfAttackPoint(AttackPoint point, double range){
        return this.getBaseVec3().distanceTo(point.getBaseVec3()) <= range;
    }

    public String toString(){
        return "[" + x + "," + y_base + "," + z + "]";
    }

}
