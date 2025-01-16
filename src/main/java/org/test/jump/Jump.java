package org.test.jump;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public final class Jump extends JavaPlugin {

    public static final Map<String, JumpNRun> jumpNRuns = new HashMap<>();
    public static final Map<Player, String> activePlayers = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getCommand("createJumpNRun").setExecutor(new CreateJumpAndRun());
        getCommand("leaveJumpNRun").setExecutor(new LeaveJumpNRun());
        getLogger().info("Jump 'n' Run Plugin aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Jump 'n' Run Plugin deaktiviert!");
    }



    public static void createAutomaticJumpNRun(String name, Location start, int length) {
        World world = start.getWorld();
        if (world == null) {
            return;
        }
        Random random = new Random();

        // Set default values
        Material platformMaterial = Material.STONE;
        int xOffset = 0;
        int yOffset = 0;
        int zOffset = 0;
        int cc;
        Location current = start.clone();

        // Add start plate
        world.getBlockAt(start.add(0, -1, 0)).setType(Material.STONE);
        start.add(0, 1, 0);
        world.getBlockAt(start).setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);

        // Set random Blocks
        for (int i = 0; i <= length; i++) {
            cc = random.nextInt(20);
            if (i == length) {
                cc = 20;
            }
            switch (cc) {
                case 1:
                    // Ladder Jumps
                    xOffset = 4;

                    current.add(xOffset, yOffset, zOffset);
                    yOffset = 1;
                    for(int j = 0; j < 4; j++) {
                        world.getBlockAt(current).setType(platformMaterial);
                        current.add(0, yOffset, 0);
                    }
                    current.add(0, -2, -1);
                    world.getBlockAt(current).setType(Material.LADDER);
                    current.add(0, -1, 0);
                    world.getBlockAt(current).setType(Material.LADDER);
                    current.add(0, -1, 0);
                    world.getBlockAt(current).setType(Material.LADDER);
                    break;
                default:
                    // Random Normal Jump
                    xOffset = random.nextInt(1, 4) + 1;
                    yOffset = random.nextInt(1, 3)-1;
                    zOffset = random.nextInt(2);
                    if (random.nextInt(2) == 1)
                        zOffset *= -1;
                    if (i == 0) {
                        yOffset = 0;
                    }
                    current.add(xOffset, yOffset, zOffset);
                    world.getBlockAt(current).setType(platformMaterial);
                    break;

            }
        }

        // Set Checkpoint Plate and Location
        Location end = current.clone();
        end.add(0, 1, 0);
        world.getBlockAt(end).setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);

        jumpNRuns.put(name, new JumpNRun(name, start, end, length));
    }




}
