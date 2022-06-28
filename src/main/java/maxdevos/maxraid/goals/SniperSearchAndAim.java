package maxdevos.maxraid.goals;

import maxdevos.maxraid.items.weapons.projecticles.SniperArrow;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;


public class SniperSearchAndAim<T extends LivingEntity> extends Goal {

    protected final Mob sniper;
    int memoryTicks = 40;
    int ticksSinceLastSeen = 0;

    int tickThreshold;
    int cooldown;
    protected final Class<T> targetType;
    protected MaxRaid raid;
    protected boolean warningShot = true;

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
        if(targetType.equals(Player.class)){
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
        }
        else{
            List<T> targets = sniper.getLevel().getNearbyEntities(targetType, TargetingConditions.forNonCombat().range(200), sniper, sniper.getBoundingBox().inflate(100f));
            for(T ent:targets){
                if(sniper.getSensing().hasLineOfSight(ent)){
                    sniper.getLookControl().setLookAt(ent);
                    if(ent != null){
                        return ent.getEyePosition();
                    }
                    return null;
                }
            }
        }

        return null;
    }

    public void tick(){
        Vec3 targetPos = findTarget();

        if(targetPos != null && sniper.getLookControl().isLookingAtTarget() && cooldown >= tickThreshold){

            if(warningShot){
//                targetPos.add(new Vec3(Math.random() * 3, Math.random() * 3, Math.random() * 3));
            }
            new SniperArrow(sniper.getBukkitEntity()).shootAtPoint(targetPos);
            warningShot = !warningShot;
            cooldown = 0;

        }
        else{
            cooldown++;
        }
    }
}
