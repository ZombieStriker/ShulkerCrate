package me.zombie_striker.shulkercrate;

import me.zombie_striker.shulkercrate.commands.KeyCommand;
import me.zombie_striker.shulkercrate.commands.ShulkerCrateCommand;
import me.zombie_striker.shulkercrate.crate.Crate;
import me.zombie_striker.shulkercrate.crate.CrateType;
import me.zombie_striker.shulkercrate.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class ShulkerCrate extends JavaPlugin {

    private HashMap<UUID, Inventory> openKeysMenues = new HashMap<>();
    private List<Crate> crateList = new LinkedList<>();
    private List<CrateType> crateTypes = new LinkedList<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this),this);
        ShulkerCrateCommand scc = new ShulkerCrateCommand(this);
        getCommand("shulkercrate").setExecutor(scc);
        getCommand("shulkercrate").setTabCompleter(scc);
        getCommand("keys").setExecutor(new KeyCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public CrateType getTypeByName(String name){
        for(CrateType crateType : crateTypes){
            if(crateType.getCrateName().equalsIgnoreCase(name))
                return crateType;
        }
        return null;
    }
    public Crate getCrate(Location location){
        for(Crate crate : crateList){
            if(crate.getLocation().equals(location)){
                return crate;
            }
        }
        return null;
    }
}
