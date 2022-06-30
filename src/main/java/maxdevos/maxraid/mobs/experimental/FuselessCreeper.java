package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.mobs.base.RaidCreeper;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.util.BlockVector;

public class FuselessCreeper extends RaidCreeper implements Spawnable {

    static MaxRaid maxRaid;

    public FuselessCreeper(MaxRaid maxRaid) {
        super(maxRaid);
        this.getHandle().maxSwell = 2;
    }

    public FuselessCreeper(MaxRaid maxRaid, BlockVector pos) {
        super(maxRaid, pos);
        this.getHandle().maxSwell = 2;
    }

}
