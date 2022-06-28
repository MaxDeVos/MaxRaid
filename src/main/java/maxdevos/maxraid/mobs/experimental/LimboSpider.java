package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.goals.LookAtPointGoal;
import maxdevos.maxraid.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.goals.targets.NearestAttackableMaxRaidTargetGoal;
import maxdevos.maxraid.goals.SpiderSpeedAttackGoal;
import maxdevos.maxraid.mobs.Spawnable;
import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.MaxReflectionUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftSpider;
import org.bukkit.util.BlockVector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** Limbo Spider can pass through single-block openings */
//TODO limbo spider (pathfinding hard)
@Deprecated
public class LimboSpider extends CraftSpider implements Spawnable {
    
    static MaxRaid maxRaid;
    public LimboSpider(MaxRaid maxRaid) {
        super(maxRaid.getHandle().getLevel().getCraftServer(), new NMSSpider(maxRaid));
        LimboSpider.maxRaid = maxRaid;
        setCustomName(ChatColor.DARK_RED + "Limbo Spider");
    }

    public LimboSpider(MaxRaid maxRaid, BlockVector loc) {
        this(maxRaid);
        spawn(loc);
    }

    public void spawn(BlockVector loc){
        this.getHandle().setPos(loc.getX(), loc.getY(), loc.getZ());
        maxRaid.addMob(this);
    }

    private static class NMSSpider extends Spider {
        MaxRaid raid;
        public NMSSpider(MaxRaid raid) {
            super(EntityType.SPIDER, raid.getHandle().serverLevel);
            this.raid = raid;
            registerRaidGoals();
//            this.maxUpStep = 256;

            EntityDimensions localDimensions = new EntityDimensions(0.9f, .9f, false);

            try {
                Field dimensionsField = MaxReflectionUtils.findByType(this.getClass().getSuperclass().getSuperclass().
                        getSuperclass().getSuperclass().getSuperclass().getSuperclass(), EntityDimensions.class);
                dimensionsField.setAccessible(true);
                dimensionsField.set(this, localDimensions);
                System.out.println(dimensionsField.getType());
                System.out.println(dimensionsField.get(this));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean canChangeDimensions() {
            return false;
        }

        @Override
        protected void registerGoals(){
            goalSelector.removeAllGoals();
            targetSelector.removeAllGoals();
        }

        protected void registerRaidGoals() {
            goalSelector.addGoal(1, new FloatGoal(this));
            goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
            goalSelector.addGoal(3, new SpiderSpeedAttackGoal(this, 2.0));
            goalSelector.addGoal(4, new MoveTowardsPointGoal(this, raid.getVillageCenter(), 1.0));

            targetSelector.addGoal(1, new HurtByTargetGoal(this));
            targetSelector.addGoal(2, new NearestAttackableMaxRaidTargetGoal<>(this, Player.class));
            targetSelector.addGoal(3, new NearestAttackableMaxRaidTargetGoal<>(this, AbstractVillager.class, true));
        }

        @Override
        public EntityDimensions getDimensions(Pose entitypose) {
            return new EntityDimensions(1f, 1f, false);
        }

    }

}
