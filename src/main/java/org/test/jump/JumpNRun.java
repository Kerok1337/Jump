package org.test.jump;

import org.bukkit.Location;

public class JumpNRun {
    private final String name;
    private final Location start;
    private final Location end;
    private final int length;

    public JumpNRun(String name, Location start, Location end, int length) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }

    public boolean isStartPlate(Location location) {
        return location.getBlockX() == start.getBlockX() &&
                location.getBlockY() == start.getBlockY() &&
                location.getBlockZ() == start.getBlockZ();
    }

    public boolean isEndPlate(Location location) {
        return location.getBlockX() == end.getBlockX() &&
                location.getBlockY() == end.getBlockY() &&
                location.getBlockZ() == end.getBlockZ();
    }
}
