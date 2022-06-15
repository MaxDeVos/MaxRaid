package maxdevos.maxraid.raid;

import maxdevos.maxraid.util.PlayerUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.event.CraftEventFactory;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class RaidFactory {

    public static @Nullable NMSRaid createOrExtendRaid(CraftWorld world, Player spigotPlayer){
        ServerLevel level = world.getHandle();
        ServerPlayer entityplayer = PlayerUtils.getNMSPlayerFromBukkitPlayer(level, spigotPlayer);
        NMSRaid raid;
        if (entityplayer.isSpectator()) {
            return null;
        } else if (level.getGameRules().getBoolean(GameRules.RULE_DISABLE_RAIDS)) {
            return null;
        } else {
            DimensionType dimensionmanager = entityplayer.level.dimensionType();
            if (!dimensionmanager.hasRaids()) {
                return null;
            } else {
                BlockPos blockposition = entityplayer.blockPosition();
                List<PoiRecord> list = level.getPoiManager().getInRange(PoiType.ALL, blockposition, 64, PoiManager.Occupancy.IS_OCCUPIED).toList();
                int i = 0;
                Vec3 vec3d = Vec3.ZERO;

                for (Iterator<PoiRecord> iterator = list.iterator(); iterator.hasNext(); ++i) {
                    PoiRecord villageplacerecord = iterator.next();
                    BlockPos blockposition1 = villageplacerecord.getPos();
                    vec3d = vec3d.add(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
                }

                BlockPos blockposition2;
                if (i > 0) {
                    vec3d = vec3d.scale(1.0 / (double) i);
                    blockposition2 = new BlockPos(vec3d);
                } else {
                    blockposition2 = blockposition;
                }


                NMSRaid r = (NMSRaid) level.getRaidAt(blockposition2);

                if (r != null) {
                    raid = r;
                } else {
                    raid = new NMSRaid(100, level, blockposition2);
                }

                boolean flag = false;
                if (!raid.isStarted()) {
                    flag = true;
                } else if (raid.isInProgress() && raid.getBadOmenLevel() < raid.getMaxBadOmenLevel()) {
                    flag = true;
                } else {
                    entityplayer.removeEffect(MobEffects.BAD_OMEN);
                    entityplayer.connection.send(new ClientboundEntityEventPacket(entityplayer, (byte) 43));
                }

                if (flag) {
                    if (!CraftEventFactory.callRaidTriggerEvent(raid, entityplayer)) {
                        entityplayer.removeEffect(MobEffects.BAD_OMEN);
                        return null;
                    }

                    if (!level.getRaids().raidMap.containsKey(raid.getId())) {
                        level.getRaids().raidMap.put(raid.getId(), raid);
                    }

                    raid.absorbBadOmen(entityplayer);
                    entityplayer.connection.send(new ClientboundEntityEventPacket(entityplayer, (byte) 43));
                    if (!raid.hasFirstWaveSpawned()) {
                        entityplayer.awardStat(Stats.RAID_TRIGGER);
                        CriteriaTriggers.BAD_OMEN.trigger(entityplayer);
                    }
                }
            }
        }
        return raid;
    }
}
