package me.zombie_striker.shulkercrate.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class GUI {

    private Inventory inventory;
    private GUICallable[] actions;

    public GUI(String name, int slots){
        this.inventory = Bukkit.createInventory(null,slots,name);
        actions=new GUICallable[slots];
    }

    public GUICallable[] getActions() {
        return actions;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void setSlotAndAction(int slot, ItemStack icon, GUICallable action){
        this.inventory.setItem(slot, icon);
        this.actions[slot]=action;
    }
}
