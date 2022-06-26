package maxdevos.maxraid.mobs.fleets;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMob;
import org.bukkit.util.BlockVector;

import java.util.LinkedList;
import java.util.Set;

public class ParatrooperFleet {

    public LinkedList<Spawnable> mobs;
    public BlockVector dropLocation;

    public ParatrooperFleet(BlockVector location){
        this.dropLocation = location;
        mobs = new LinkedList<>();
    }

    public void addMobToFleet(Spawnable mob){
        mobs.add(mob);
    }

    public void dropAll(){
        System.out.println("DROPPING FLEET");
        for(Spawnable mob:mobs){
            mob.spawn(dropLocation);
        }
    }


}
