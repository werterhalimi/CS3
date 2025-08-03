package ch.shai.cs3.commands;

import ch.shai.cs3.utils.parsing.ParsingSlot;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;

public abstract class CSCommand implements CommandExecutor {

    protected boolean isAdmin = false;
    private ParsingSlot slots[];
    public CSCommand(ParsingSlot... slots){
        this.slots = slots;
    }


    protected abstract void execute(Player player, Location location, World world, String label);

    public ArrayList<String> toLore(){
        ArrayList<String> ret = new ArrayList<>();
        for (ParsingSlot slot : slots) {
            if (slot.getValue(null) != null && slot.isItemable())
                ret.add("-" + slot.getKey() + " " + slot.getValue(null));
        }
        return ret;
    }


    protected void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public void fillValues(String args[]) throws ParseException {
        for (int i = 0; i < args.length - 1; i++) {
            String token = args[i];
            String value = args[i + 1];
            for (ParsingSlot slot : slots) {
                slot.checkToken(token, value);
            }
        }
    }

    public ParsingSlot[] getSlots() {
        return slots;
    }

    protected ParsingSlot getSlot(int i) {
        return slots[i];
    }

    protected void emptyValues() {
        for (ParsingSlot slot : slots) {
            slot.emptyValue();
        }
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            World world = location.getWorld();
            try {
                this.fillValues(args);
                for (ParsingSlot slot : this.slots) {
                    if (slot.isMandatory() && !slot.hasValue()) {
                        player.sendMessage(slot.getKey() + " n'est pas optionnel");
                        return false;
                    }
                }
                this.execute(player,location,world, command.getLabel());
            }catch (ParseException e) {
                player.sendMessage(e.getMessage());
            }
            this.emptyValues();
        }
        return false;
    }

}
