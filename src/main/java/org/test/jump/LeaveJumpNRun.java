package org.test.jump;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.test.jump.Jump.activePlayers;

public class LeaveJumpNRun  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Leave Jump And Run
        if (activePlayers.containsKey(Bukkit.getPlayer(commandSender.getName()))) {
            activePlayers.remove(Bukkit.getPlayer(commandSender.getName()));
            commandSender.sendMessage("Left the Jump and Run");
            return true;
        } else {
            return false;
        }
    }
}
