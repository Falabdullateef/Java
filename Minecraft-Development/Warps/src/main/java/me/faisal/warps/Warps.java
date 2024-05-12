package me.faisal.warps;

import me.faisal.warps.commands.*;
import me.faisal.warps.listeners.PlayerRespawnListener;
import me.faisal.warps.listeners.PlayerJoinListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Warps extends JavaPlugin {
    
    // This is the config section
    // -----------------------------------
    
    private static final String prefix = "<#45bbe6>SkyXGenz<reset> »";
    
    // -----------------------------------
    
    @Override
        public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);


        // Commands
        Objects.requireNonNull(this.getCommand("warp")).setExecutor(new warpcmd());
        Objects.requireNonNull(this.getCommand("mines")).setExecutor(new minescmd());
        Objects.requireNonNull(this.getCommand("crates")).setExecutor(new cratescmd());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new spawncmd());
        Objects.requireNonNull(this.getCommand("warps")).setExecutor(new warplistcmd());
        Objects.requireNonNull(this.getCommand("setwarp")).setExecutor(new SetWarps(this));
        System.out.println("@ Warps plugin has been enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("@ Warps plugin has been disabled");
    }

    public static void TeleportMines(Player target){
        Location mineslocation;

        mineslocation = Warps.getPlugin(Warps.class).getConfig().getLocation("mines");
        if (mineslocation == null){
            mineslocation = new Location(Bukkit.getWorld("flat"), -204, 51.5, -271, 180, 0);
        }
        target.teleport(mineslocation);
        TeleportMessage(target, "mines");
        TeleportSound(target);
    }

    public static void NoPermissions(@NotNull CommandSender sender, String sound) {
        @NotNull MiniMessage mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(prefix + " <red>You do not have permission to use this command!");
        sender.sendMessage(parsed);
        Player player = (Player) sender;
        player.playSound(player.getLocation(), sound, 1, 2);
    }

    public static void TeleportCrates(Player target) {
        Location crateslocation;
        crateslocation = Warps.getPlugin(Warps.class).getConfig().getLocation("crates");
        if (crateslocation == null){
            crateslocation = new Location(Bukkit.getWorld("flat"), -580.5, 132.5, -276.5, -180, 0);
        }
        target.teleport(crateslocation);
        TeleportMessage(target, "crates");
        TeleportSound(target);
    }
    public static void TeleportPlot(Player player){
        player.performCommand("plot home");
        //wait for 10 ticks
        TeleportSound(player);
        TeleportMessage(player, "your plot");
    }

    public static void TeleportSpawn(Player target) {
        // Bukkit.getWorld("flat"), -340.5, 183.5, -259.55, -90, 0);
        Location spawnlocation;
        spawnlocation = Warps.getPlugin(Warps.class).getConfig().getLocation("spawn");
        if (spawnlocation == null){
            spawnlocation = new Location(Bukkit.getWorld("flat"), -340.5, 183.5, -259.55, -90, 0);
        }
        target.teleport(spawnlocation);
        TeleportMessage(target, "spawn");
        TeleportSound(target);
    }

    private static void TeleportSound(Player target) {
        target.playSound(target.getLocation(), "entity.enderman.teleport", 1, 2);
        target.playSound(target.getLocation(), "entity.experience_orb.pickup", 1, 2);
    }

    public static void WarpsList(Player target) {
        target.sendMessage("Warps available: spawn, crates, plot, mines");
        target.playSound(target.getLocation(), "block.note_block.bell", 1, 1);
    }
    public static void TeleportMessage(Player whotosendto, String location){
        @NotNull MiniMessage mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(prefix + " <white>You have been teleported to " + location + "!");
        whotosendto.sendMessage(parsed);
    }
    public static void PrefixMessage(Player whotosendto, String message){
        @NotNull MiniMessage mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(prefix+ " <white>" + message);
        whotosendto.sendMessage(parsed);
    }
}

