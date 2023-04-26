package me.zombie_striker.shulkercrate.crate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CrateType {
    private String crateName;
    private String displayname;
    private Material crateBlockType;
    private HashMap<Reward,Double> rewards = new HashMap<>();

    public CrateType(String name,String displayname, Material crateBlockType){
        this.crateName = name;
        this.crateBlockType = crateBlockType;
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public HashMap<Reward, Double> getRewards() {
        return rewards;
    }

    public Material getCrateBlockType() {
        return crateBlockType;
    }

    public String getCrateName() {
        return crateName;
    }
}
