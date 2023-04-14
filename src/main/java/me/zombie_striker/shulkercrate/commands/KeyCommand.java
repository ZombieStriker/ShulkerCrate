package me.zombie_striker.shulkercrate.commands;

import me.zombie_striker.shulkercrate.ShulkerCrate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class KeyCommand implements CommandExecutor {
    private ShulkerCrate shulkerCrate;
    public KeyCommand(ShulkerCrate shulkerCrate) {
        this.shulkerCrate = shulkerCrate;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}
