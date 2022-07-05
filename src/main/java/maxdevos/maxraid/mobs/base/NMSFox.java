package maxdevos.maxraid.mobs.base;

import maxdevos.maxraid.goals.GrazeGoal;
import maxdevos.maxraid.mobs.RaidMob;
import maxdevos.maxraid.raid.MaxRaid;
import maxdevos.maxraid.util.VecUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;

public class NMSFox extends Fox implements RaidMob {
    MaxRaid raid;

    public NMSFox(MaxRaid raid) {
        super(EntityType.FOX, raid.getHandle().serverLevel);
        this.raid = raid;
        this.getBukkitEntity().setCustomName(ChatColor.RED + "Raid Fox");
        this.maxUpStep=2;
    }

    @Override
    public void tick(){
        super.tick();

        int minX = (int) Math.floor(this.getBoundingBox().minX);
        int minY = (int) Math.floor(this.getBoundingBox().minY);
        int minZ = (int) Math.floor(this.getBoundingBox().minZ);

        int maxX = (int) Math.ceil(this.getBoundingBox().maxX);
        int maxY = (int) Math.ceil(this.getBoundingBox().maxY);
        int maxZ = (int) Math.ceil(this.getBoundingBox().maxZ);

        for(int x = minX; x < maxX; x++){
            for(int y = minY; y < maxY; y++){
                for(int z = minZ; z <= maxZ; z++){
                    Block current = level.getWorld().getBlockAt(x,y,z);
                    if(current.getBlockData().getAsString().toLowerCase().split("\\[")[0].equals("minecraft:sweet_berry_bush")){
                        level.setBlockAndUpdate(VecUtil.locToBPos(current.getLocation()), Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
    }

    public void registerRaidGoals() {
        goalSelector.addGoal(1, new GrazeGoal(this));
    }
}