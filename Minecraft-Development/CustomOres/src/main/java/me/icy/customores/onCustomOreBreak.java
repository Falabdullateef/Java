package me.icy.customores;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onCustomOreBreak implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        // Check the type of block broken
        Material blockType = event.getBlock().getType();
        if (blockType == Material.DIORITE) {
            // Create custom drop for Diorite
            ItemStack titanium = new ItemStack(Material.DIORITE, 1);
            ItemMeta titaniumMeta = titanium.getItemMeta();
            if (titaniumMeta != null) {
                titaniumMeta.setDisplayName("Raw Titanium");
                titanium.setItemMeta(titaniumMeta);
            }
            // Set the custom drop
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), titanium);
        } else if (blockType == Material.GRANITE) {
            // Create custom drop for Granite
            ItemStack uranium = new ItemStack(Material.GRANITE, 1);
            ItemMeta uraniumMeta = uranium.getItemMeta();
            if (uraniumMeta != null) {
                uraniumMeta.setDisplayName("Raw Uranium");
                uranium.setItemMeta(uraniumMeta);
            }
            // Set the custom drop
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), uranium);
        }
    }
}
