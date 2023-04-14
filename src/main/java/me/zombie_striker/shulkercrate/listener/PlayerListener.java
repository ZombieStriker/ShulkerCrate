package me.zombie_striker.shulkercrate.listener;

import me.zombie_striker.shulkercrate.ShulkerCrate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
}
