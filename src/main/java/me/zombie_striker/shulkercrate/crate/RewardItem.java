package me.zombie_striker.shulkercrate.crate;

import org.bukkit.inventory.ItemStack;

public class RewardItem implements Reward {

    private ItemStack item;
    private double chanceOfReward;

    public RewardItem(ItemStack is, double chance){
        this.item = is;
        this.chanceOfReward = chance;
    }
    @Override
    public double getChanceOfReward() {
        return chanceOfReward;
    }

    public ItemStack getItem() {
        return item;
    }
}
