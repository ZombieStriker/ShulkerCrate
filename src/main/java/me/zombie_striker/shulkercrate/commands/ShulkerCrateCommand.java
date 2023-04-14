package me.zombie_striker.shulkercrate.commands;

import me.zombie_striker.shulkercrate.ShulkerCrate;
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

        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
