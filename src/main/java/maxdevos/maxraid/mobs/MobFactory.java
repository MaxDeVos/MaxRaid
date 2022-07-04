package maxdevos.maxraid.mobs;

import maxdevos.maxraid.items.Equipper;
import maxdevos.maxraid.items.armor.RaidArmor;
import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.VecTools;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMob;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMonster;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockVector;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/** Java doesn't support multiple inheritance, so here we are. */
public class MobFactory {

    public static CraftMob createMob(MaxRaid raid, Class<? extends Mob> type) {
        return createCraftMob(raid, type, false, null, 0.0f, null,
                null, 0.0f, null);
    }

    public static CraftMob createMob(MaxRaid raid, Mob mob){
        return createMob(raid, mob, false, null, 0.0f, null, null, 0.0f, null);
    }


    public static CraftMob createMob(MaxRaid raid, Mob mob, boolean spawn, BlockVector pos){
        return createMob(raid, mob, spawn, pos, 0.0f, null, null, 0.0f, null);
    }

    public static CraftMob createMob(MaxRaid raid, Mob mob, boolean spawn,
                                     BlockVector pos, float health, ItemStack weapon, RaidArmor armor,
                                     float speed, String customNameTag){

//        if(Arrays.stream(mob.getClass().getInterfaces()).noneMatch(Predicate.isEqual(RaidMob.class))){
//            System.err.println("FAILED TO SPAWN " + mob.getClass().getName() + "! Doesn't implement RaidMob");
//            return null;
//        }

        try {

            String[] fullClassName = mob.getClass().getName().split("\\.");
            String className = fullClassName[fullClassName.length - 1];
            System.out.println(className);

            mob.goalSelector.removeAllGoals();
            mob.targetSelector.removeAllGoals();

            if(speed != 0.0f){
                try{
                    mob.getClass().getDeclaredMethod("setSpeedMultiplier").invoke(mob, speed);
                } catch (NoSuchMethodException ignored){

                }
            }

            mob.getClass().getDeclaredMethod("registerRaidGoals").invoke(mob);

            if(health != 0.0f){
                if(mob.getAttribute(Attributes.MAX_HEALTH) != null){
                    Objects.requireNonNull(mob.getAttribute(Attributes.MAX_HEALTH)).addPermanentModifier(
                            new AttributeModifier("raid bonus", health, AttributeModifier.Operation.ADDITION));
                    mob.setHealth(health);
                }
            }

            if(mob.getBukkitEntity() instanceof CraftMonster){
                Equipper.setMobArmor((CraftMonster) mob.getBukkitEntity(), armor);
                Equipper.setMobWeapon((CraftMonster) mob.getBukkitEntity(), weapon);
            }

            if(customNameTag != null){
                mob.getBukkitEntity().setCustomName(customNameTag);
            }

            if(pos != null){
                mob.setPos(VecTools.blockVectorToVec3(pos));
            }

            CraftMob castedMob = (CraftMob) mob.getBukkitEntity();

            if(spawn){
                raid.addMob(castedMob);
            }

            return castedMob;

        } catch (NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /** If you ever need the raw instance of the NMS Mob, you're doing something wrong.
     *  In the event that you find yourself needing to do this, call me so that I can yell at you myself: 9529234747 */
    public static CraftMob createCraftMob(MaxRaid raid, Class<? extends Mob> type, boolean spawn,
                                          BlockVector pos, float health, ItemStack weapon, RaidArmor armor,
                                          float speed, String customNameTag) {
        try {

            Mob mob = type.getDeclaredConstructor(MaxRaid.class).newInstance(raid);
            return createMob(raid, mob, spawn, pos, health, weapon, armor, speed, customNameTag);

        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
