package ch.shai.cs3.utils.itemstack;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WhoolUtils {

    public static ItemStack getColoredWool(DyeColor color, String name) {
        ItemStack ret = getColoredWool(color);
        ItemMeta meta = ret.getItemMeta();
        meta.setDisplayName(name);
        ret.setItemMeta(meta);
        return ret;
    }


    public static ItemStack getColoredWool(DyeColor color) {
        String materialName = color.name() + "_WOOL";
        Material woolMaterial = Material.matchMaterial(materialName);
        if (woolMaterial == null) throw new IllegalArgumentException("Invalid color: " + color);
        return new ItemStack(woolMaterial);
    }
}
