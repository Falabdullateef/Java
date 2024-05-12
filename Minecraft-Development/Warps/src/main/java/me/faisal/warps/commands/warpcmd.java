package me.faisal.warps.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.faisal.warps.Warps.*;

public class warpcmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length >0 && args.length <=2) {
                Player player = (Player) sender;
                if (args.length == 1){
                    switch(args[0].toLowerCase()){
                        case "spawn":
                            TeleportSpawn(player);
                            break;
                        case "mines":
                            TeleportMines(player);
                            break;
                        case "crates":
                            TeleportCrates(player);
                            break;
                        case "plot":
                            TeleportPlot(player);
                            break;
                        default:
                            WarpsList(player);
                            return false;
                    }
                    return true;
                } else {
                    if (sender.hasPermission("warps.warp.others")){
                        Player target = player.getServer().getPlayer(args[1]);
                        if (target == null) {
                            //custom message using minimessage
                            PrefixMessage(player, "<red>Player not found!");
                            player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                            return true;
                        } else {
                            switch(args[0].toLowerCase()){
                                case "spawn":
                                    TeleportSpawn(target);
                                    break;
                                case "mines":
                                    TeleportMines(target);
                                    break;
                                case "crates":
                                    TeleportCrates(target);
                                    break;
                                case "plot":
                                    TeleportPlot(target);
                                    break;
                                default:
                                    WarpsList(player);
                                    return false;
                            }
                            return true;
                        }
                }else{
                    NoPermissions(sender, "entity.villager.no");
                    }
                }
            }
        }
    return false;
    }
}
