package me.icywater.banwords;

import me.icywater.banwords.listeners.onChat;
import org.bukkit.plugin.java.JavaPlugin;

public final class BanWords extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getLogger().info("[BanWords] Plugin enabled!");
        getServer().getPluginManager().registerEvents(new onChat(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
