package me.zombie_striker.shulkercrate.listener;

import me.zombie_striker.shulkercrate.ShulkerCrate;
import me.zombie_striker.shulkercrate.crate.RewardItem;
import me.zombie_striker.shulkercrate.gui.GUI;
import me.zombie_striker.shulkercrate.gui.GUICallable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    private ShulkerCrate shulkerCrate;

    public InventoryListener(ShulkerCrate crate){
        this.shulkerCrate = crate;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(shulkerCrate.getOpenKeysMenues().containsValue(event.getClickedInventory())){
            event.setCancelled(true);
            return;
        }
        if(shulkerCrate.getRewardMenues().containsValue(event.getClickedInventory())){
            event.setCancelled(true);
            if(event.getCurrentItem()!=null){
                RewardItem rewardItem = new RewardItem(event.getCurrentItem(),1);

            }
            return;
        }
        if(shulkerCrate.getGUIs().containsKey(event.getWhoClicked().getUniqueId())){
            GUI gui = shulkerCrate.getGUIs().get(event.getWhoClicked().getUniqueId());
            if(gui.getInventory().equals(event.getInventory())) {
                event.setCancelled(true);
                GUICallable c = gui.getActions()[event.getRawSlot()];
                if(c!=null)
                    c.call();
                return;
            }
        }
    }
}
