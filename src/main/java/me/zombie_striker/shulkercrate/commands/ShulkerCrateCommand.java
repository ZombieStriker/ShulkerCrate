package me.zombie_striker.shulkercrate.commands;

import me.zombie_striker.shulkercrate.ShulkerCrate;
import me.zombie_striker.shulkercrate.crate.Crate;
import me.zombie_striker.shulkercrate.crate.CrateType;
import me.zombie_striker.shulkercrate.gui.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShulkerCrateCommand implements CommandExecutor, TabExecutor {
    private ShulkerCrate shulkerCrate;
    public ShulkerCrateCommand(ShulkerCrate shulkerCrate) {
    this.shulkerCrate = shulkerCrate;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED+"Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        if(args.length==0||args[0].equalsIgnoreCase("help")){
            sender.sendMessage("--==Commandes==--");
            sender.sendMessage("/sc placecrate <type> : Places a crate at your location.");
            return true;
        }
        if(args[0].equalsIgnoreCase("addItemPrize")) {
            if (!sender.hasPermission("shulkercrates.commands.addprize")) {
                sender.sendMessage("You do not have permission to use this command.");
                return true;
            }
            Inventory prizes = Bukkit.createInventory(null,0,"Click on an inventory item to add a prize.");
            shulkerCrate.getRewardMenues().put(player.getUniqueId(),prizes);
            player.openInventory(prizes);
            return true;
        }

        if(args[0].equalsIgnoreCase("placecrate")){
            if(!sender.hasPermission("shulkercrates.commands.placecrate")){
                sender.sendMessage("You do not have permission to use this command.");
                return true;
            }
            if(args.length == 1){
                sender.sendMessage(ChatColor.RED+" You need to provide a crate type");
                return true;
            }
            CrateType type = shulkerCrate.getTypeByName(args[1]);
            if(type==null){
                sender.sendMessage(ChatColor.RED+"Could not find crate "+args[1]+".");
                return true;
            }
            player.getLocation().getBlock().setType(type.getCrateBlockType());
            Crate crate = new Crate(player.getLocation(),type);
            shulkerCrate.registerCrate(crate);
            sender.sendMessage("Crate "+type.getDisplayname()+" created.");
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
