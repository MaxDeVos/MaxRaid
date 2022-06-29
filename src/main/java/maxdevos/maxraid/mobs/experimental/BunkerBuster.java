package maxdevos.maxraid.mobs.experimental;

import maxdevos.maxraid.RaidPlugin;
import maxdevos.maxraid.raid.MaxRaid;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockVector;


import java.util.HashSet;
import java.util.Set;

public class BunkerBuster extends BukkitRunnable {

    World world;
    BlockVector loc;
    Set<BlockVector> circlePositions;
    int depth;
    int ticks;
    final int countdownTicks = 80;

    public BunkerBuster(MaxRaid raid, BlockVector loc, int depth, int radius){
        this(raid.getHandle().getLevel().getWorld(), loc, depth, radius);
    }

    public BunkerBuster(World world, BlockVector loc, int depth, int radius){
        this.world = world;
        this.loc = new BlockVector(loc.getX(), world.getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()), loc.getZ());
        this.depth = depth;
        circlePositions = new HashSet<>();

        int centerX = loc.getBlockX();
        int centerZ = loc.getBlockZ();

        for(int xRel = 0; xRel <= radius; xRel++){
            for(int zRel = 0; zRel <= radius; zRel++){
                if(Math.sqrt(Math.pow(xRel,2) + Math.pow(zRel,2)) <= radius + 0.5){
                    circlePositions.add(new BlockVector(centerX + xRel, 0, centerZ + zRel));
                    circlePositions.add(new BlockVector(centerX - xRel, 0, centerZ - zRel));
                    circlePositions.add(new BlockVector(centerX + xRel, 0, centerZ - zRel));
                    circlePositions.add(new BlockVector(centerX - xRel , 0, centerZ + zRel));
                }
            }
        }
    }

    public void initiate(){
        this.runTaskTimer(RaidPlugin.getInstance(), 0, 1);
    }

    public void detonate(){
        for(int y = loc.getBlockY(); y > loc.getBlockY() - depth; y--){
            for(BlockVector xzPos:circlePositions){
                world.playSound(new Location(world, loc.getX(), loc.getY(), loc.getZ()), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 2, 2);
                world.getBlockAt(xzPos.getBlockX(), y, xzPos.getBlockZ()).breakNaturally();
            }
        }
    }

    @Override
    public void run() {
        if(ticks % 5 == 0){
            float pitch = 0.5f + ((ticks / (float) countdownTicks));
            for(int y = loc.getBlockY() + 15; y >= loc.getBlockY() - depth; y--) {
                world.playSound(new Location(world, loc.getX(), y, loc.getZ()), Sound.BLOCK_COMPARATOR_CLICK, 2, pitch);
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(Math.round(((ticks / (float) countdownTicks)) * 255), 0, 0), 1.0F);
                for(BlockVector block:circlePositions){
                    for(int i = 0; i < 1; i++){
                        double particleX = (block.getX() + 0.5) + (Math.random() - 0.5);
                        double particleY = (y - 0.5) + (Math.random() - 0.5);
                        double particleZ = (block.getZ() + 0.5) + (Math.random() - 0.5);
                        world.spawnParticle(Particle.REDSTONE, particleX, particleY, particleZ, 1, 0, -0.25, 0, dustOptions);
                    }
                }
            }
        }
        if(ticks == countdownTicks){
            detonate();
            this.cancel();
        }
        ticks++;
    }
}
