package ch.shai.cs3.utils.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryValueUtils {
    public static double gradePlayerInventory(Player player){
        Inventory inventory = player.getInventory();
        return (inventory.getContents().length + 1) * 5;
    }
}
