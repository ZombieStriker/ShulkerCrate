package me.zombie_striker.shulkercrate.crate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;

public class CrateType {
    private String crateName;
    private String displayname;
    private Material crateBlockType;
    private LinkedList<Reward> rewards = new LinkedList<>();

    public CrateType(String name,String displayname, Material crateBlockType){
        this.crateName = name;
        this.crateBlockType = crateBlockType;
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public LinkedList<Reward> getRewards() {
        return rewards;
    }

    public Material getCrateBlockType() {
        return crateBlockType;
    }

    public String getCrateName() {
        return crateName;
    }
}
