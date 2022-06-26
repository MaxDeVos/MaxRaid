package maxdevos.maxraid.items.weapons.projecticles;

import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;

public class SniperArrow extends BaseArrow {

    public SniperArrow(CraftEntity archer) {
        super(archer);

        velocity = 10f;
        spread = 0.001f;
        getHandle().setNoGravity(true);
    }
}
