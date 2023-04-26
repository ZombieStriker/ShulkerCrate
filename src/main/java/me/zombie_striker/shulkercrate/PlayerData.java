package me.zombie_striker.shulkercrate;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    private HashMap<String,Integer> keys = new HashMap<>();
    private UUID player;

    public PlayerData(UUID uuid){
        this.player = uuid;
    }

    public HashMap<String, Integer> getKeys() {
        return keys;
    }
    public void loadKeys(FileConfiguration config){
        if(config.contains("keys")){
            for(String type : config.getConfigurationSection("keys").getKeys(false)){
                keys.put(type,config.getInt("keys."+type));
            }
        }
    }
    public void saveKeys(FileConfiguration config){
        for(Map.Entry<String, Integer> e : keys.entrySet()){
            config.set("keys."+e.getKey(),e.getValue());
        }
    }

    public UUID getPlayer() {
        return player;
    }
}
