package maxdevos.maxraid.mobs;

import maxdevos.maxraid.items.Equipper;
import maxdevos.maxraid.items.armor.RaidArmor;
import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.VecTools;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftMonster;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockVector;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Predicate;

public class MonsterFactory {
    public static CraftMonster createCraftMonster(MaxRaid raid, Class<? extends Monster> type) {
        return createCraftMonster(raid, type, false, null, 0.0f, null, null, 0.0f, null);
    }

    /** If you ever need the raw instance of the NMS Mob, you're doing something wrong */
    public static CraftMonster createCraftMonster(MaxRaid raid, Class<? extends Monster> type, boolean spawn, BlockVector pos, float health, ItemStack weapon, RaidArmor armor,
                                            float speed, String customNameTag) {

        if(Arrays.stream(type.getInterfaces()).noneMatch(Predicate.isEqual(RaidMob.class))){
            System.err.println("FAILED TO SPAWN " + type.getName() + "! Doesn't implement RaidMob");
            return null;
        }

        try {

            Monster monster = type.getDeclaredConstructor(MaxRaid.class).newInstance(raid);
            monster.goalSelector.removeAllGoals();
            monster.targetSelector.removeAllGoals();

            if(speed != 0.0f){
                monster.setSpeed(speed);
            }

            type.getDeclaredMethod("registerRaidGoals").invoke(monster);

            if(health != 0.0f){
                monster.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("raid bonus", health, AttributeModifier.Operation.ADDITION));
                monster.setHealth(health);
            }

            Equipper.setMobArmor((CraftMonster) monster.getBukkitEntity(), armor);
            Equipper.setMobWeapon((CraftMonster) monster.getBukkitEntity(), weapon);

            if(customNameTag != null){
                monster.getBukkitEntity().setCustomName(customNameTag);
            }

            if(pos != null){
                monster.setPos(VecTools.blockVectorToVec3(pos));
            }

            CraftMonster castedMonster = (CraftMonster) monster.getBukkitEntity();

            if(spawn){
                raid.addMob(castedMonster);
            }

            return castedMonster;


        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
