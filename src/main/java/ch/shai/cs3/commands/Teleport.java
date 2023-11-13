package ch.shai.cs3.commands;

import ch.shai.cs3.utils.parsing.ParsingSlotString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class Teleport extends CSCommand{

    public Teleport(){
        super(
                new ParsingSlotString("Met -[name/n] <nom> le nom du nom pour te tp dessus", "name","n").setMandatory(true)
        );
    }

    @Override
    protected void execute(Player player, Location location, World world, String label) {
        String name = (String) this.getSlot(0).getValue();
        WorldCreator wc = new WorldCreator(name);
        player.teleport(Bukkit.createWorld(wc).getSpawnLocation());
    }
}
