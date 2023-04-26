package me.zombie_striker.shulkercrate;

import me.zombie_striker.shulkercrate.commands.KeyCommand;
import me.zombie_striker.shulkercrate.commands.ShulkerCrateCommand;
import me.zombie_striker.shulkercrate.crate.Crate;
import me.zombie_striker.shulkercrate.crate.CrateType;
import me.zombie_striker.shulkercrate.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class ShulkerCrate extends JavaPlugin {

    private HashMap<UUID, Inventory> openKeysMenues = new HashMap<>();
    private List<Crate> crateList = new LinkedList<>();
    private List<CrateType> crateTypes = new LinkedList<>();

    private File shulkertypesConfigFile;
    private File crateDataFile;

    private FileConfiguration stc;
    private FileConfiguration cd;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this),this);
        ShulkerCrateCommand scc = new ShulkerCrateCommand(this);
        getCommand("shulkercrate").setExecutor(scc);
        getCommand("shulkercrate").setTabCompleter(scc);
        getCommand("keys").setExecutor(new KeyCommand(this));

        shulkertypesConfigFile = new File(getDataFolder(),"crateTypes.yml");
        if(shulkertypesConfigFile.exists()){
            stc = YamlConfiguration.loadConfiguration(shulkertypesConfigFile);
            stc.set("crates.DEFAULT.material", Material.SHULKER_BOX.name());
            stc.set("crates.DEFAULT.displayname", "Default Crate");
            try {
                stc.save(shulkertypesConfigFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            stc = YamlConfiguration.loadConfiguration(shulkertypesConfigFile);
        }

        if(stc.contains("crates")){
            for(String key : stc.getConfigurationSection("crates").getKeys(false)){
                Material material = Material.matchMaterial(stc.getString("crates."+key+".material"));
                String displayname = stc.getString("crates."+key+".displayname");
                CrateType sc = new CrateType(key,displayname,material);
                crateTypes.add(sc);
            }
        }



        crateDataFile = new File(getDataFolder(),"cratedata.yml");
        cd = YamlConfiguration.loadConfiguration(crateDataFile);
    }

    @Override
    public void onDisable() {

    }

    public List<CrateType> getCrateTypes() {
        return crateTypes;
    }

    public List<Crate> getCrateList() {
        return crateList;
    }

    public void registerCrate(Crate crate){
        this.crateList.add(crate);
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
