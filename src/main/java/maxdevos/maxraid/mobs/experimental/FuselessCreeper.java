package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.mobs.base.RaidCreeper;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.util.BlockVector;

public class FuselessCreeper extends RaidCreeper {

    static MaxRaid maxRaid;
    public FuselessCreeper(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid, loc);
        this.getHandle().maxSwell = 2;
    }

}
