package maxdevos.maxraid.goals;

import maxdevos.maxraid.items.weapons.projecticles.SniperArrow;
import maxdevos.maxraid.raid.MaxRaid;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;


public class SniperSearchAndAim<T extends LivingEntity> extends Goal {

    protected final Mob sniper;
    int memoryTicks = 40;
    int ticksSinceLastSeen = 0;

    int tickThreshold;
    int cooldown;
    protected final Class<T> targetType;
    protected MaxRaid raid;
    protected boolean criticalShot = true;

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

            if(!criticalShot){
                targetPos.add(new Vec3(Math.random() * 2, Math.random() * 2, Math.random() * 2));
            }
            new SniperArrow(sniper.getBukkitEntity()).shootAtPoint(targetPos);
            criticalShot = Math.random() > 0.25;
            cooldown = 0;

        }
        else{
            cooldown++;
        }
    }
}
