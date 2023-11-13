package ch.shai.cs3.commands.completers;

import ch.shai.cs3.utils.parsing.ParsingSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TpxCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String last = strings[strings.length - 1];
        List<String> ret = new ArrayList<>();

        if (last == null|| last.contentEquals(""))
            ret.add("-");
        else if (last.contains("-n")) {
            for (World world : Bukkit.getWorlds()) {
                ret.add(world.getName());
            }
        }
        return ret;
    }
}
