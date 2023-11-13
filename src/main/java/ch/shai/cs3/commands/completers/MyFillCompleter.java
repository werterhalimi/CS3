package ch.shai.cs3.commands.completers;

import ch.shai.cs3.commands.CSCommand;
import ch.shai.cs3.commands.MyFill;
import ch.shai.cs3.utils.parsing.ParsingSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyFillCompleter implements TabCompleter {

    private CSCommand command;

    public MyFillCompleter(CSCommand command) {
        this.command = command;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String last = strings[strings.length - 1];
        List<String> ret = new ArrayList<>();
        if (last == null|| last.contentEquals(""))
            ret.add("-");
        else if (last.contentEquals("-") && this.command.getSlots().length > 0) {
            if (strings.length <= 1)
            commandSender.sendMessage(ChatColor.GREEN + "========================= " + ChatColor.BLUE + "Description" + ChatColor.GREEN + "============");
            for (ParsingSlot slot : this.command.getSlots()) {
                ret.add("-" + slot.getKey());
                if (strings.length <= 1)
                commandSender.sendMessage(ChatColor.GRAY + "[ " + ChatColor.GOLD + "description" + ChatColor.GRAY + " ] = " + ChatColor.BOLD + ChatColor.WHITE + slot.getDescription());
            }
        }
        return ret;
    }
}
