package me.faisal.gamemodes.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.faisal.gamemodes.Gamemodes.GamemodeChange;
import static me.faisal.gamemodes.Gamemodes.PrefixMessage;

public class GmspCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("gmsp")) {
            Player player = (Player) sender;
            if (player.isOp()) {
                //possible usages:
                // gmsp
                // gmsp <player>
                if (args.length > 1) {
                    player.sendMessage("Invalid usage! Use /gmsp <player>");
                    player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                } else {
                    if (args.length == 0) {
                        GamemodeChange(player, "spectator");
                    } else {
                        Player target = player.getServer().getPlayer(args[0]);
                        if (target == null) {
                            player.sendMessage("Player not found!");
                            player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                        } else {
                            PrefixMessage(player, "<green> Player " + target.getName() + " has been set to spectator mode!");
                            GamemodeChange(target, "spectator");
                        }
                    }
                }
            } else {
                player.sendMessage("You do not have permission to use this command!");
                player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
            }
        }
        return true;
    }
}
