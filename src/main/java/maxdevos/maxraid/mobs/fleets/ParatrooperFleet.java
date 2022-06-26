package maxdevos.maxraid.mobs.fleets;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMob;
import org.bukkit.util.BlockVector;

import java.util.LinkedList;
import java.util.Set;

public class ParatrooperFleet {

    public LinkedList<? extends Spawnable> mobs;

    public void dropAll(BlockVector loc){
        for(Spawnable mob:mobs){
            mob.spawn(loc);
        }
    }


}
