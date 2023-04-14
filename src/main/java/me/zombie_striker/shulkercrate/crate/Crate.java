package me.zombie_striker.shulkercrate.crate;

import org.bukkit.Location;

public class Crate {

    private CrateType type;
    private Location location;
    private boolean inUse;

    public Crate(Location location, CrateType type){
        this.type = type;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public CrateType getType() {
        return type;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
