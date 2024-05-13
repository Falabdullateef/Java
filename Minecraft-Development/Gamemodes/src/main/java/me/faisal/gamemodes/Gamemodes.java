package me.faisal.gamemodes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Gamemodes extends JavaPlugin {
    // config section

    // -----------------------------------

    private static final String prefix = "<#45bbe6>SkyXGenz<reset> »";

    // -----------------------------------


    @Override
    public void onEnable() {
        System.out.println("@ Gamemodes plugin has enabled (icy)");
        Objects.requireNonNull(this.getCommand("gamemode")).setExecutor(new me.faisal.gamemodes.Commands.GamemodeCommand());
        Objects.requireNonNull(this.getCommand("gmc")).setExecutor(new me.faisal.gamemodes.Commands.GmcCommand());
        Objects.requireNonNull(this.getCommand("gms")).setExecutor(new me.faisal.gamemodes.Commands.GmsCommand());
        Objects.requireNonNull(this.getCommand("gma")).setExecutor(new me.faisal.gamemodes.Commands.GmaCommand());
        Objects.requireNonNull(this.getCommand("gmsp")).setExecutor(new me.faisal.gamemodes.Commands.GmspCommand());

    }

    @Override
    public void onDisable() {
        System.out.println("@ Gamemodes plugin has disabled (icy)");
    }
    public static void PrefixMessage(Player whotosendto, String message){
        @NotNull MiniMessage mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(prefix+ " <white>" + message);
        whotosendto.sendMessage(parsed);
    }
    public static void GamemodeChange(Player player, String gamemode) {
        player.setGameMode(org.bukkit.GameMode.valueOf(gamemode.toUpperCase()));
        PrefixMessage(player, "<green>Your gamemode has been set to " + gamemode + " mode!");
        player.playSound(player.getLocation(), "block.note_block.pling", 1, 2);

    }
}
