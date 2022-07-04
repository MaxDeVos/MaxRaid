package maxdevos.maxraid.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Color;

import javax.annotation.Nullable;

public class MobStats {

    public float health;
    public Color armorColor;
    public float speed;
    public String displayName;

    public MobStats(float health, @Nullable Color armorColor, float speed, @Nullable String displayName){
        this();
        if(health != 0.0f){
            this.health = health;
        }
        if(armorColor != null){
            this.armorColor = armorColor;
        }
        if(speed != 0.0f){
            this.speed = speed;
        }
        if(displayName != null){
            this.displayName = displayName;
        }
    }

    public MobStats(){
        health = 20f;
        armorColor = Color.fromRGB(86, 52, 33);
        speed = 1f;
        displayName = ChatColor.DARK_RED + "Raid Mob";
    }

}
