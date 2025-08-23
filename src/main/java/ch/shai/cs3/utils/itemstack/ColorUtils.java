package ch.shai.cs3.utils.itemstack;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public class ColorUtils {
    public static NamedTextColor dyeColorToChatColor(DyeColor dyeColor){
        NamedTextColor color;
        try {
            color = NamedTextColor.NAMES.value(dyeColor.name().toLowerCase());
        } catch (IllegalArgumentException e) {
            color = NamedTextColor.WHITE;
        }
        return color;
    }
}
