package me.icywater.banwords.listeners;

import me.icywater.banwords.BanWords;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChat implements Listener {
    private final BanWords plugin;

    public onChat(BanWords banWords) {
        this.plugin = banWords;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String[] message;
        Player player = event.getPlayer();
        int Length = (message = event.getMessage().split(" ")).length;

        for(int i =0; i < Length; i++){
            String word = message[i].toLowerCase();
            if(plugin.getConfig().getStringList("banned-words").contains(word)){
                event.setCancelled(true);

                String msg;
                if (plugin.getConfig().getString("banned-words-message") == null){
                    msg = ChatColor.DARK_RED+ "[AutoMod] " +ChatColor.RED + "You cannot swear in chat!";
                }else{
                    msg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("banned-words-message"));
                }
                player.sendMessage(msg);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 2, 1);
            }
        }

    }
}
