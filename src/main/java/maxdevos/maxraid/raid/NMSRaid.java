package maxdevos.maxraid.raid;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.events.StopRaidEvent;
import maxdevos.maxraid.mobs.base.RaidMonster;
import maxdevos.maxraid.util.ChatFunctions;
import maxdevos.maxraid.util.MaxReflectionUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class NMSRaid extends Raid implements Listener {

    public ArrayList<Mob> raidMobs = new ArrayList<>();
    ServerBossEvent bossEvent; // called raidEvent in NMS
    public ServerLevel serverLevel;
    public float maxHealth = 0.0F;

    protected NMSRaid(int i, ServerLevel world, BlockPos pos){
        super(i, world, pos);
        this.serverLevel = world;
        RaidPlugin.getInstance().getServer().getPluginManager().registerEvents(this, RaidPlugin.getInstance());
        bossEvent = new ServerBossEvent(new TranslatableComponent("event.minecraft.raid"),
                BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_20);
        try {
            Field raidEventField = MaxReflectionUtils.findByType(this.getClass().getSuperclass(), ServerBossEvent.class);
            raidEventField.set(this, bossEvent);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    /** Inject new raidEvent to control raid bar */
    private void generateNewRaidEvent(){

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

    public void addMob(RaidMonster raidMonster){
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
    public float getHealthOfLivingRaiders() {
        float currentHealth = 0.0F;
        for(Mob mob:raidMobs){
            currentHealth += mob.getHealth();
        }
        return currentHealth;
    }

    public void recalculateMaxHealth(){
        maxHealth = 0.0F;
        for(Mob mob:raidMobs){
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

//    @EventHandler
//    private void newWave(RaidSpawnWaveEvent e) {
//        recalculateMaxHealth();
//        RaidPlugin.getInstance().getServer().broadcastMessage("DETECTED NEW WAVE | " +
//                "RECALCULATED MAXHEALTH TO = " + maxHealth);
//        updateBossbar();
//    }
//
//    @EventHandler
//    private void endRaid(RaidFinishEvent e){
//        RaidPlugin.getInstance().getServer().broadcastMessage(ChatFunctions.raidPrefix + "The Raid is Over.");
////        PlayerUtils.printKillOrder(players);
////        HandlerList.unregisterAll(this);
////        scoreboard.restoreScoreboard();
////        handler.unregister();
//    }
//
    @EventHandler
    private void endRaid(StopRaidEvent e){
        serverLevel.getWorld().setGameRule(GameRule.DISABLE_RAIDS, true);
//        killAll();
//        Bukkit.getPluginManager().callEvent(new RaidFinishEvent(, serverLevel.getWorld(), RaidPlayer.getPlayerListFromUUIDs(heroesOfTheVillage)));
        RaidPlugin.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(RaidPlugin.getInstance(), () ->
                serverLevel.getWorld().setGameRule(GameRule.DISABLE_RAIDS, false), 2L);
    }
}
