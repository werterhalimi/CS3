package ch.shai.cs3.utils.logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.nio.Buffer;

public class LoggerUtils {
    public static final LoggerMod CURRENT_MOD = LoggerMod.DEBUG;
    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + "CraftMine" + ChatColor.GRAY + "]" + ChatColor.WHITE + ": ";

    public static void announce(String msg){
        Bukkit.broadcastMessage(LoggerUtils.PREFIX + msg);
    }
    
    public static void debug(String msg){
        if (LoggerUtils.CURRENT_MOD == LoggerMod.DEBUG) LoggerUtils.announce(msg);
    }
}
