package me.zombie_striker.shulkercrate.listener;

import me.zombie_striker.shulkercrate.PlayerData;
import me.zombie_striker.shulkercrate.ShulkerCrate;
import me.zombie_striker.shulkercrate.crate.Crate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class PlayerListener implements Listener {
    private ShulkerCrate shulkerCrate;

    public PlayerListener(ShulkerCrate shulkerCrate) {
        this.shulkerCrate = shulkerCrate;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

        } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Crate c = null;
        if ((c = shulkerCrate.getCrate(event.getBlock().getLocation())) != null) {
            if (event.getPlayer().hasPermission("shulkercrates.destroycrates")) {
                shulkerCrate.destroyCrate(c);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        File playerdata = shulkerCrate.getPlayerFile(event.getPlayer().getUniqueId());
        if (playerdata.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(playerdata);
            PlayerData playerData = new PlayerData(event.getPlayer().getUniqueId());
            playerData.loadKeys(fc);
            shulkerCrate.getPlayerdata().put(event.getPlayer().getUniqueId(), playerData);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        File playerdata = shulkerCrate.getPlayerFile(event.getPlayer().getUniqueId());

        FileConfiguration fc = YamlConfiguration.loadConfiguration(playerdata);
        PlayerData playerData = shulkerCrate.getPlayerdata().get(event.getPlayer().getUniqueId());
        playerData.saveKeys(fc);
        try {
            fc.save(playerdata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
