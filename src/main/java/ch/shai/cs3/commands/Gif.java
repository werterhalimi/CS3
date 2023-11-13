package ch.shai.cs3.commands;

import ch.shai.cs3.utils.gif.GifRunnable;
import ch.shai.cs3.utils.parsing.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

import static ch.shai.cs3.utils.gif.GifUtils.readGif;

public class Gif extends CSCommand{

    private JavaPlugin plugin;

    public Gif(JavaPlugin plugin){
        super (
                new ParsingSlotString("Le nom du gif", "gif", "g").setMandatory(true),
                new ParsingSlotDouble("La largeur du panneau", "width", "w"),
                new ParsingSlotDouble("La hauteur du panneau", "height", "h"),
                new ParsingSlotDouble("La precision", "precision", "p")

        );
        this.plugin = plugin;
    }


    @Override
    protected void execute(Player player, Location location, World world, String label) {
        try {
            int[][] pixels = readGif( new File("gifs/" + (String) this.getSlot(0).getValue(null)));
            int height = pixels[0][0];
            int width = pixels[0][1];
            Location base = location.add(0,((ParsingSlot<Integer>)this.getSlot(2)).getValue(15),0);
            double xTmp = base.getX();
            new GifRunnable(height,width,base,player,pixels,xTmp).runTaskTimerAsynchronously(this.plugin, 0, 30);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
