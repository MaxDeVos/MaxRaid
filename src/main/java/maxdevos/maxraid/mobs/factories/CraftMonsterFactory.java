package maxdevos.maxraid.mobs.factories;

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

public class CraftMonsterFactory {

    public static CraftMonster createCraftMonster(MaxRaid raid, Class<? extends Monster> type) {
        return createCraftMonster(raid, type, null, 0.0f, null, null, 0.0f, null);
    }

    public static CraftMonster createCraftMonster(MaxRaid raid, Class<? extends Monster> type, BlockVector pos, float health, ItemStack weapon, RaidArmor armor,
                                  float speed, String customNameTag) {
        return (CraftMonster) (MonsterFactory.createNMSMonster(raid, type, pos, health, weapon, armor, speed, customNameTag).getBukkitEntity());
    }

    public static void spawnMonster(MaxRaid raid, Class<? extends Monster> type, BlockVector pos, float health, ItemStack weapon, RaidArmor armor,
                                    float speed, String customNameTag){
        raid.addMob(createCraftMonster(raid, type, pos, health, weapon, armor, speed, customNameTag));
    }

}
