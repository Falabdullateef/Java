package me.faisal.gamemodes.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.faisal.gamemodes.Gamemodes.PrefixMessage;

public class GamemodeCommand implements CommandExecutor {

    private static String[] GameModes = {"survival", "creative", "adventure", "spectator"};
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("gamemode")) {
            Player player = (Player) sender;
            if (player.isOp()){
                if(args.length ==2) { //gamemode <gamemode> <player>
                    Player target = player.getServer().getPlayer(args[1]);
                    if (target == null){
                        PrefixMessage(player, "<red>Player not found!");
                        player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                    }else{
                        GameModeChanger(args, target, player);
                    }
                }else if (args.length == 1){ //gamemode <gamemode>
                    GameModeChanger(args, player, player);
                }else{
                    PrefixMessage(player, "<red>Invalid usage! Use /gamemode <gamemode> <player>");
                    player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
                }
            }else{
                PrefixMessage(player, "<red>You do not have permission to use this command!");
                player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
            }
        }
        return true;
    }

    private static void GameModeChanger(@NotNull String @NotNull [] args, Player target, Player player) {
        String gamemode = args[0].toLowerCase();
        for (String gm : GameModes){
            if (gamemode.equals(gm)){
                target.setGameMode(GameMode.valueOf(gamemode.toUpperCase()));
                PrefixMessage(player, "<green>Player " + target.getName() + " has been set to " + gamemode + " mode!");
                PrefixMessage(target, "<green>You have been set to " + gamemode + " mode!");
                //bell sound for both players
                player.playSound(player.getLocation(), "BLOCK_NOTE_BLOCK_PLING", 1, 2);
                target.playSound(target.getLocation(), "BLOCK_NOTE_BLOCK_PLING", 1, 2);
            }else{
                PrefixMessage(player, "<red>Invlaid gamemode! Use survival, creative, adventure, spectator");
                player.playSound(player.getLocation(), "entity.villager.no", 1, 2);
            }
        }
    }
}
