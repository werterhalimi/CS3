package ch.shai.cs3.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerInteract implements Listener {

    @EventHandler()
    public void onPlayerInteract(PlayerInteractEvent event){
        if (event.getAction().name().toLowerCase().contains("right") && event.hasItem()){
            ItemStack item = event.getItem();
            if (item.getType().equals(Material.NAME_TAG) && item.getItemMeta().getLore() != null){
                List<String> lore = item.getItemMeta().getLore();
                Player player = event.getPlayer();
                StringBuilder builder = new StringBuilder(lore.get(0));
                for (int i = 1; i < lore.size(); i++)
                    builder.append(" " + lore.get(i));
                player.performCommand(item.getItemMeta().getDisplayName() + " " + builder);
                System.out.println("Commandew :" + item.getItemMeta().getDisplayName() + " " + builder);
            }
        }
    }

}
