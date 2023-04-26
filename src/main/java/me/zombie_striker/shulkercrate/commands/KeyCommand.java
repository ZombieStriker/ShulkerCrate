package me.zombie_striker.shulkercrate.commands;

import me.zombie_striker.shulkercrate.ShulkerCrate;
import me.zombie_striker.shulkercrate.crate.CrateType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class KeyCommand implements CommandExecutor {
    private ShulkerCrate shulkerCrate;
    public KeyCommand(ShulkerCrate shulkerCrate) {
        this.shulkerCrate = shulkerCrate;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Inventory i = Bukkit.createInventory(null,(((shulkerCrate.getCrateTypes().size()/9)+1)*9),"Keys");
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Only players can use this command.");
            return true;
        }
        for(Map.Entry<String, Integer> key : shulkerCrate.getPlayerdata().get(((Player) commandSender).getUniqueId()).getKeys().entrySet()){
            CrateType ct = shulkerCrate.getTypeByName(key.getKey());
            if(ct!=null){
                ItemStack is = new ItemStack(ct.getCrateBlockType(),key.getValue());
                ItemMeta im = is.getItemMeta();
                im.setDisplayName("Key for: "+ct.getDisplayname());
                is.setItemMeta(im);
                i.addItem(is);
            }
        }
        ((Player) commandSender).openInventory(i);
        shulkerCrate.getOpenKeysMenues().put(((Player) commandSender).getUniqueId(),i);
        return true;
    }
}
