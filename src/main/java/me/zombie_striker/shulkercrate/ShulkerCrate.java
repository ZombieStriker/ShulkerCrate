package me.zombie_striker.shulkercrate;

import me.zombie_striker.shulkercrate.commands.KeyCommand;
import me.zombie_striker.shulkercrate.commands.ShulkerCrateCommand;
import me.zombie_striker.shulkercrate.crate.Crate;
import me.zombie_striker.shulkercrate.crate.CrateType;
import me.zombie_striker.shulkercrate.gui.GUI;
import me.zombie_striker.shulkercrate.listener.InventoryListener;
import me.zombie_striker.shulkercrate.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class ShulkerCrate extends JavaPlugin {

    private HashMap<UUID, Inventory> openKeysMenues = new HashMap<>();
    private HashMap<UUID, Inventory> rewardMenues = new HashMap<>();
    private HashMap<UUID, GUI> GUIS = new HashMap<>();
    private List<Crate> crateList = new LinkedList<>();
    private List<CrateType> crateTypes = new LinkedList<>();
    private HashMap<UUID,PlayerData> playerdata = new HashMap<>();

    private File shulkertypesConfigFile;
    private File crateDataFile;

    private FileConfiguration stc;
    private FileConfiguration cd;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);

        ShulkerCrateCommand scc = new ShulkerCrateCommand(this);
        getCommand("shulkercrate").setExecutor(scc);
        getCommand("shulkercrate").setTabCompleter(scc);
        getCommand("keys").setExecutor(new KeyCommand(this));

        shulkertypesConfigFile = new File(getDataFolder(), "crateTypes.yml");
        if (shulkertypesConfigFile.exists()) {
            stc = YamlConfiguration.loadConfiguration(shulkertypesConfigFile);
            stc.set("crates.DEFAULT.material", Material.SHULKER_BOX.name());
            stc.set("crates.DEFAULT.displayname", "Default Crate");
            try {
                stc.save(shulkertypesConfigFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            stc = YamlConfiguration.loadConfiguration(shulkertypesConfigFile);
        }

        if (stc.contains("crates")) {
            for (String key : stc.getConfigurationSection("crates").getKeys(false)) {
                Material material = Material.matchMaterial(stc.getString("crates." + key + ".material"));
                String displayname = stc.getString("crates." + key + ".displayname");
                CrateType sc = new CrateType(key, displayname, material);
                crateTypes.add(sc);
            }
        }


        crateDataFile = new File(getDataFolder(), "cratedata.yml");
        cd = YamlConfiguration.loadConfiguration(crateDataFile);
        if (cd.contains("cratesAt")) {
            for (String key1 : cd.getConfigurationSection("cratesAt").getKeys(false)) {
                for (String key2 : cd.getConfigurationSection("cratesAt." + key1).getKeys(false)) {
                    for (String key3 : cd.getConfigurationSection("cratesAt." + key1 + "." + key2).getKeys(false)) {
                        for (String key4 : cd.getConfigurationSection("cratesAt." + key1 + "." + key2 + "." + key3).getKeys(false)) {
                            World world = Bukkit.getWorld(key1);
                            int x = Integer.parseInt(key3);
                            int y = Integer.parseInt(key2);
                            int z = Integer.parseInt(key4);
                            Location crate = new Location(world, x, y, z);

                            String type = cd.getString("cratesAt." + key1 + "." + key2 + "." + key3 + "." + key4 + ".type");
                            CrateType type1 = getTypeByName(type);
                            Crate c = new Crate(crate,type1);
                            registerCrate(c);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDisable() {
        for (Crate c : crateList) {
            cd.set("cratesAt." + c.getLocation().getWorld().getName() + "." + c.getLocation().getBlockY() + "." + c.getLocation().getBlockX() + "." + c.getLocation().getBlockZ() + ".type", c.getType().getCrateName());
        }
        try {
            cd.save(crateDataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CrateType> getCrateTypes() {
        return crateTypes;
    }

    public List<Crate> getCrateList() {
        return crateList;
    }

    public void registerCrate(Crate crate) {
        this.crateList.add(crate);
    }

    public CrateType getTypeByName(String name) {
        for (CrateType crateType : crateTypes) {
            if (crateType.getCrateName().equalsIgnoreCase(name))
                return crateType;
        }
        return null;
    }

    public Crate getCrate(Location location) {
        for (Crate crate : crateList) {
            if (crate.getLocation().equals(location)) {
                return crate;
            }
        }
        return null;
    }

    public void destroyCrate(Crate crate) {
        this.crateList.remove(crate);
    }

    public HashMap<UUID, PlayerData> getPlayerdata() {
        return playerdata;
    }

    public File getPlayerFile(UUID player){
        File folder = new File(getDataFolder(),"playerdata");
        if(!folder.exists())
            folder.mkdirs();
        return new File(folder,player.toString()+".yml");
    }

    public HashMap<UUID, Inventory> getOpenKeysMenues() {
        return openKeysMenues;
    }

    public HashMap<UUID, Inventory> getRewardMenues() {
        return rewardMenues;
    }

    public HashMap<UUID, GUI> getGUIs() {
        return GUIS;
    }
}
