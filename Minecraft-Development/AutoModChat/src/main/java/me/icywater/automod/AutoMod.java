package me.icywater.automod;

import me.icywater.automod.listeners.onChat;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoMod extends JavaPlugin {
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onChat(this), this);
        System.out.println("[IcyWater] AutoMod has been enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("[IcyWater] AutoMod has been disabled!");
        System.out.println("[IcyWater] AutoMod has been disabled!");
    }
}
