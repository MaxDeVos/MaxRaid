package maxdevos.maxcraft.raidSystem.NMSMobs;

import maxdevos.maxcraft.MaxPlugin;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.Location;

import java.util.EnumSet;

public class CustomMeleeAttack extends PathfinderGoal {

    EntityInsentient a;
    private EntityLiving b;
    private double c;
    private double d;
    private double e;
    private int i = 0;

    CustomMeleeAttack(EntityInsentient e){
        this.a(EnumSet.of(Type.MOVE));
        this.a = e;
    }

    @Override
    public boolean a() {
        this.b = this.a.getGoalTarget();
        if(this.b == null){
            return false;
        }
        this.c = this.b.locX();
        this.d = this.b.locY();
        this.e = this.b.locZ();
        return true;
    }

    public void c(){
        a.getNavigation().a(this.c,this.d,this.e,2.0);
        if(Math.sqrt(a.getPositionVector().distanceSquared(b.getPositionVector())) <= 3 && i>=20){
            i=0;
        }
        else{
            i++;
        }
    }

    public boolean b(){
        return !a.getNavigation().m();
    }

}
