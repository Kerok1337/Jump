package org.test.jump;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import java.util.Objects;

import static org.test.jump.Jump.*;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(org.bukkit.event.player.PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock() != null) {
            Material blockType = event.getClickedBlock().getType();

            // Join Jump And Run
            if (blockType == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
                for (JumpNRun jumpNRun : jumpNRuns.values()) {
                    if (jumpNRun.isStartPlate(event.getClickedBlock().getLocation()) && !activePlayers.containsKey(player)) {
                        activePlayers.put(player, jumpNRun.getName());
                        player.sendMessage("Du hast das Jump 'n' Run '" + jumpNRun.getName() + "' betreten!");
                        return;
                    } else if (jumpNRun.isEndPlate(event.getClickedBlock().getLocation()) && Objects.equals(activePlayers.get(player), jumpNRun.getName())) {
                        // Reach Checkpoint
                        player.sendMessage("Checkpoint erreicht!");
                        activePlayers.remove(player);

                        // create new module if user reached the checkpoint
                        if (jumpNRuns.containsKey(jumpNRun.getName()+"next")) {
                          activePlayers.put(player, jumpNRun.getName()+"next");
                        } else {
                            createAutomaticJumpNRun(jumpNRun.getName() + "next", jumpNRun.getEnd(), jumpNRun.getLength());
                        }
                    }
                }
            }
        }
    }
}
