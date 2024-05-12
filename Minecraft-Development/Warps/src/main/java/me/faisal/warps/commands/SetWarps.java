package me.faisal.warps.commands;

import me.faisal.warps.Warps;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.Prefix;
import org.jetbrains.annotations.NotNull;

import static me.faisal.warps.Warps.PrefixMessage;

public class SetWarps implements CommandExecutor {
    private final Warps plugin;

    public SetWarps(Warps plugin) {
        this.plugin = plugin;
    }

    private final String[] warpargs = {"spawn", "crates", "mines"};



    // Command structure /setwarp spawn, /setwarp crates, /setwarp mines
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("setwarp")) {
            if (sender instanceof Player player) {
                if (player.hasPermission("warps.setwarp")) {
                    if (args.length == 1) {
                        String warpName = args[0].toLowerCase();
                        for (String warparg : warpargs) {
                            if (warpName.equals(warparg)) {
                                Location location = player.getLocation();
                                plugin.getConfig().set(warpName, location);
                                plugin.saveConfig();
                                PrefixMessage(player, "<green>Location of " + warpName + " has been set!");
                                return true;
                            }
                        }
                        PrefixMessage(player, "<red>Invalid warp name! Use /setwarp spawn, /setwarp crates, /setwarp mines");
                        player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                        return true;
                    }
                }else {
                    PrefixMessage(player, "<red>You do not have permission to use this command!");
                    player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                    return true;
                }
            } else {
                PrefixMessage((Player) sender, "<red>You must be a player to use this command");
                return true;
            }

        }
        return true;
    }
}