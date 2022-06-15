package maxdevos.maxraid.raid;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.util.MaxReflectionUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftMonster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.raid.RaidTriggerEvent;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

public class NMSRaid extends Raid implements Listener {

    public ArrayList<LivingEntity> raidMobs = new ArrayList<>();
    ServerBossEvent bossEvent; // called raidEvent in NMS
    public ServerLevel serverLevel;
    public World bukkitWorld;
    public float maxHealth = 0.0F;

    public MaxRaid maxRaid;

    protected NMSRaid(int i, ServerLevel world, BlockPos pos){
        super(i, world, pos);
        this.serverLevel = world;
        this.bukkitWorld = world.getWorld();
        RaidPlugin.getInstance().getServer().getPluginManager().registerEvents(this, RaidPlugin.getInstance());
        generateNewRaidEvent();
    }

    /** Inject new raidEvent to control raid bar */
    private void generateNewRaidEvent(){
        bossEvent = new ServerBossEvent(new TranslatableComponent("event.minecraft.raid"),
                BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_20);
        try {
            Field raidEventField = MaxReflectionUtils.findByType(this.getClass().getSuperclass(), ServerBossEvent.class);
            raidEventField.set(this, bossEvent);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    @Override
    public void joinRaid(int i, Raider entityraider, @Nullable BlockPos blockposition, boolean flag) {
        super.joinRaid(i, entityraider, blockposition, flag);
        addMob(entityraider);
    }

    public void addMob(Raider raidMonster){
        this.raidMobs.add(raidMonster);
        this.maxHealth += raidMonster.getMaxHealth();
        recalculateMaxHealth();
    }

    public void addMob(LivingEntity raidMonster){
        serverLevel.addFreshEntity(raidMonster, CreatureSpawnEvent.SpawnReason.RAID);
        this.raidMobs.add(raidMonster);
        this.maxHealth += raidMonster.getMaxHealth();
        recalculateMaxHealth();
    }

    @Override
    public void removeFromRaid(Raider entityRaider, boolean flag){
        super.removeFromRaid(entityRaider, flag);
        raidMobs.remove(entityRaider);
    }

    @Override
    public int getTotalRaidersAlive(){
        int livingCounter = 0;
        for(LivingEntity ent:raidMobs){
            if(ent.isAlive()){
                livingCounter++;
            }
        }
        return livingCounter;
    }

    @Override
    public float getHealthOfLivingRaiders() {
        float currentHealth = 0.0F;
        for(LivingEntity mob:raidMobs){
            currentHealth += mob.getHealth();
        }
        return currentHealth;
    }

    public void recalculateMaxHealth(){
        maxHealth = 0.0F;
        for(LivingEntity mob:raidMobs){
            maxHealth += mob.getMaxHealth();
        }
    }

    public float calculateProgressFloat(){
        float calc = this.getHealthOfLivingRaiders() / this.maxHealth;
        if(calc >= 0){
            return calc;
        }
        return 1;
    }

    @Override
    public void updateBossbar() {
        this.bossEvent.setProgress(calculateProgressFloat());
//        RaidPlugin.getInstance().getServer().broadcastMessage(this.getHealthOfLivingRaiders() + " / " + this.maxHealth + "     " + this.bossEvent.getProgress());
    }

    public boolean isUUIDRaider(UUID mobUUID){
        for(LivingEntity m:raidMobs){
            if(m.getUUID().equals(mobUUID)){
                return true;
            }
        }
        return false;
    }

    @EventHandler
    private void triggerRaid(RaidTriggerEvent e){
        maxRaid = new MaxRaid(this, e.getRaid());
    }

    public void unregister(){
        HandlerList.unregisterAll(this);
    }
}
