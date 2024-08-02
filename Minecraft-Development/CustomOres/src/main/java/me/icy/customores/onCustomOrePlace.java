package me.icy.customores;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onCustomOrePlace implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event){
        Material block = event.getBlock().getType();
        if(block == Material.DIORITE){
            event.setCancelled(true);
        }else if (block == Material.GRANITE){
            event.setCancelled(true);
        }
    }
}
