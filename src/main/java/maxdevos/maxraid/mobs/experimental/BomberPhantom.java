package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.DropBombs;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPhantom;
import org.bukkit.util.BlockVector;

//TODO make this work (very hard)
@Deprecated
public class BomberPhantom extends CraftPhantom {

    static MaxRaid maxRaid;
    public BomberPhantom(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSPhantom(maxRaid));
        BomberPhantom.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "Bomber Phantom");
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.getHandle().addMob(this.getHandle());
    }

    private static class NMSPhantom extends Phantom {
        MaxRaid raid;
        public NMSPhantom(MaxRaid raid) {
            super(EntityType.PHANTOM, raid.getHandle().serverLevel);
            this.raid = raid;
//            registerRaidGoals();
//            this.getMoveControl().setWantedPosition(0, 20, 0, 1);
        }

//        @Override
//        protected void registerGoals(){
////            goalSelector.removeAllGoals();
//            targetSelector.removeAllGoals();
//        }

        protected void registerRaidGoals() {
            goalSelector.addGoal(0, new DropBombs(this));
//            goalSelector.addGoal(2, new ZombieAttackGoal(this, 2.0, true));
//            goalSelector.addGoal(3, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));
//            goalSelector.addGoal(4, new LookAtPointGoal(this, raid.getVillageCenter()));

//            targetSelector.addGoal(1, new HurtByTargetGoal(this));
            targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        }

        /** Make immune from sunburn */
        @Override
        protected boolean isSunBurnTick() {
            return false;
        }
    }

}
