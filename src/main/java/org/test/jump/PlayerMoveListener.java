package org.test.jump;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.test.jump.Jump.activePlayers;
import static org.test.jump.Jump.jumpNRuns;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Teleport Player back if fell off

        if (activePlayers.containsKey(player)) {
            String jumpNRunName = activePlayers.get(player);
            JumpNRun jumpNRun = jumpNRuns.get(jumpNRunName);

            if (event.getTo().getY() < jumpNRun.getStart().getY()-2) {
                player.teleport(jumpNRun.getStart());
            }
        }
    }
}
