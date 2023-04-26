package me.zombie_striker.shulkercrate.commands;

import me.zombie_striker.shulkercrate.ShulkerCrate;
import me.zombie_striker.shulkercrate.crate.Crate;
import me.zombie_striker.shulkercrate.crate.CrateType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
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
            sender.sendMessage("/");
            return true;
        }
        if(args[0].equalsIgnoreCase("placecrate")){
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
            
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
