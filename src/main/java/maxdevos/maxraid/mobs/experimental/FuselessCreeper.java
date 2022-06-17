package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.mobs.base.RaidCreeper;
import maxdevos.maxraid.mobs.goals.LookAtPointGoal;
import maxdevos.maxraid.mobs.goals.MoveTowardsPointGoal;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftCreeper;
import org.bukkit.util.BlockVector;

public class FuselessCreeper extends RaidCreeper {

    static MaxRaid maxRaid;
    public FuselessCreeper(MaxRaid maxRaid, BlockVector loc) {
        super(maxRaid, loc);
        this.getHandle().maxSwell = 2;
    }

}
