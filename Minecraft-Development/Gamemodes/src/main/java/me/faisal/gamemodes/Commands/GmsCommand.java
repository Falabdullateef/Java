package me.faisal.gamemodes.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.Prefix;
import org.jetbrains.annotations.NotNull;

import static me.faisal.gamemodes.Gamemodes.GamemodeChange;
import static me.faisal.gamemodes.Gamemodes.PrefixMessage;

public class GmsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("gms")) {
            Player player = (Player) sender;
            if (player.isOp()) {
                //possible usages:
                // gms
                // gms <player>
                if (args.length > 1) {
                    PrefixMessage(player, "<red>Invalid usage! Use /gms <player>");
                    player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                } else {
                    if (args.length == 0) {
                        GamemodeChange(player, "survival");
                    } else {
                        Player target = player.getServer().getPlayer(args[0]);
                        if (target == null) {
                            PrefixMessage(player, "<red>Player not found!");
                            player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                        } else {
                            PrefixMessage(player, "<green>Player " + target.getName() + " has been set to survival mode!");
                            GamemodeChange(target, "survival");
                        }
                    }
                }
            } else {
                PrefixMessage(player, "<red>You do not have permission to use this command!");
                player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
            }
        }
        return true;
    }
}
