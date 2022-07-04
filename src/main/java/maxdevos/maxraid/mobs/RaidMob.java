package maxdevos.maxraid.mobs;

public interface RaidMob {

    void registerRaidGoals();

    default void setSpeedMultiplier(float speed){}

}
