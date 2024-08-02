package me.icy.itemimmunity;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class onItemDamage implements Listener {
    @EventHandler
    public void onItemDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Item){
            EntityDamageEvent.DamageCause cause = event.getCause();
            if (cause == EntityDamageEvent.DamageCause.LAVA || cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.CONTACT){
                event.setCancelled(true);
            }
        }
    }
}