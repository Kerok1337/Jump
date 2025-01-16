package org.test.jump;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.test.jump.Jump.createAutomaticJumpNRun;

public class CreateJumpAndRun implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 2) {
                player.sendMessage("Verwendung: /createJumpNRun <Name> <Länge>");
                return true;
            }

            String name = args[0];
            int length;

            try {
                length = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("Die Länge muss eine Zahl sein.");
                return true;
            }

            Location start = player.getLocation();
            createAutomaticJumpNRun(name, start, length);
            player.sendMessage("Jump 'n' Run '" + name + "' mit Länge " + length + " erstellt!");
            return true;
        } else {
            sender.sendMessage("Dieser Befehl kann nur von einem Spieler ausgeführt werden.");
            return true;
        }
    }
}
