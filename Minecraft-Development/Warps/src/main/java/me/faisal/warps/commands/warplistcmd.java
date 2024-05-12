package me.faisal.warps.commands;

import me.faisal.warps.Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class warplistcmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("warps")) {
            if (sender instanceof Player) {
                Warps.WarpsList((Player) sender);
            } else {
                sender.sendMessage("You must be a player to use this command");
            }
            return true;
        }
        return false;
    }
}
