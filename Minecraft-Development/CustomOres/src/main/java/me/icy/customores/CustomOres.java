package me.icy.customores;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomOres extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("[ ICYWATER ] >> CustomOres has been enabled!");
        getServer().getPluginManager().registerEvents(new onCustomOrePlace(), this);
        getServer().getPluginManager().registerEvents(new onCustomOreBreak(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("[ ICYWATER ] >> CustomOres has been disabled!");
    }
}
