package maxdevos.maxraid.items.weapons.projecticles;

import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;

public class SniperArrow extends BaseArrow {

    public SniperArrow(CraftEntity archer) {
        super(archer);
    }

    /** Override this to modify arrow */
    @Override
    public void configureArrow(){
        velocity = 1000f;
        spread = 0.001f;
        getHandle().setNoGravity(true);
    }

}
