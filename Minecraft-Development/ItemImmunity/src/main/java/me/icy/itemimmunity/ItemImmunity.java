package me.icy.itemimmunity;

import org.bukkit.plugin.java.JavaPlugin;

public final class ItemImmunity extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new onItemDamage(), this);
        getLogger().info("[Icywater] >> ItemsImmunity has been enabled successfully!");

    }

    @Override
    public void onDisable() {
        getLogger().info("[Icywater] >> ItemsImmunity has been disabled successfully!");
    }
}
