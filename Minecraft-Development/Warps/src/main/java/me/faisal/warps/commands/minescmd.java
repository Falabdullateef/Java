package me.faisal.warps.commands;

import me.faisal.warps.Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class minescmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("mines")) {
            if (sender.hasPermission("warps.warp")) {
                Warps.TeleportMines((Player) sender);
                return true;
            } else {
                Warps.NoPermissions(sender, "entity.villager.no");
                return true;
            }
        }
        return false;
    }
}
