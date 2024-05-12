package me.faisal.warps.listeners;

import me.faisal.warps.Warps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    private final Warps plugin;

    public PlayerRespawnListener(Warps plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Location spawnLocation = plugin.getConfig().getLocation("spawn");
        if (spawnLocation == null) {
            spawnLocation = new Location(Bukkit.getWorld("flat"), -340.5, 183.5, -259.55, -90, 0);
        }
        event.setRespawnLocation(spawnLocation);
    }
}
