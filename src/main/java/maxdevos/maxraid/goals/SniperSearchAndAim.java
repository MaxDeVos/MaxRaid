package maxdevos.maxraid.goals;

import maxdevos.maxraid.items.weapons.projecticles.SniperArrow;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nullable;
import java.util.UUID;


public class SniperSearchAndAim<T extends LivingEntity> extends Goal {

    protected final Mob sniper;
    int memoryTicks = 40;
    int ticksSinceLastSeen = 0;

    int tickThreshold;
    int cooldown;
    protected final Class<T> targetType;
    protected MaxRaid raid;

    public SniperSearchAndAim(Mob sniper, MaxRaid raid, int cooldown, Class<T> oclass) {
        this.targetType = oclass;
        this.sniper = sniper;
        this.raid = raid;
        this.tickThreshold = cooldown;
    }

    public boolean canUse() {
        return true;
    }

    protected Vec3 findTarget() {
        for(org.bukkit.entity.Player p: raid.getHandle().getLevel().getWorld().getPlayers()){
            Player player = sniper.getLevel().getPlayerByUUID(p.getUniqueId());
            if(sniper.getSensing().hasLineOfSight(player)){
                sniper.getLookControl().setLookAt(player);
                if(player != null){
                    return player.getEyePosition();
                }
                return null;
            }
        }
        return null;
    }

    public void tick(){
        Vec3 targetPos = findTarget();

        if(targetPos != null && sniper.getLookControl().isLookingAtTarget() && cooldown >= tickThreshold){
            new SniperArrow(sniper.getBukkitEntity()).shootAtPoint(targetPos);
            cooldown = 0;
        }
        else{
            cooldown++;
        }
    }
}
