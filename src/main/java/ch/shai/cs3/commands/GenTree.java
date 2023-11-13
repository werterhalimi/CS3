package ch.shai.cs3.commands;

import ch.shai.cs3.utils.parsing.ParsingSlotBool;
import ch.shai.cs3.utils.parsing.ParsingSlotDouble;
import ch.shai.cs3.utils.parsing.ParsingSlotInt;
import ch.shai.cs3.utils.tree.TreeGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class GenTree extends CSCommand implements CommandExecutor {

    private HashMap<Player, TreeGenerator> hashMap;
    public GenTree(){
        super(
                new ParsingSlotInt("Le nombre de troncs", "noTrunk", "nt"),
                new ParsingSlotDouble("Le rayon de chaque tronc", "rayon", "r", "radius"),
                new ParsingSlotInt("La hauteur du tronc" ,"hauteur", "h"),
                new ParsingSlotInt("La largeur totale de l'arbre genre les feuilles tsais", "largeur", "l", "x", "width"),
                new ParsingSlotInt("La taille de l'arbre a partir de la fin du tronc", "taille", "t", "y", "height"),
                new ParsingSlotDouble("Taux d'intersection [0.00 a 1.00]]", "i", "nb"),
                new ParsingSlotBool("Si je dois dessiner les branches", "branches", "b"),
                new ParsingSlotBool("Si je dois dessiner les nodes", "node","n")
        );
        this.hashMap = new HashMap<>();
    }


    @Override
    protected void execute(Player player, Location location, World world, String label) {
        double radius = (double) this.getSlot(1).getValue(5.0);
        int trunkHeight = (int) this.getSlot(2).getValue(0);
        int width = (int) this.getSlot(3).getValue(50);
        int height = (int) this.getSlot(4).getValue(100);
        int nb = (int) this.getSlot(5).getValue(100);
        boolean b = (boolean) this.getSlot(6).getValue(false);
        boolean node = (boolean) this.getSlot(7).getValue(true);

        Location begin = location.clone().add(0, trunkHeight, 0);

        if (!this.hashMap.containsKey(player) ) this.hashMap.put(player, new TreeGenerator(begin, height,trunkHeight,height, nb, radius));
        TreeGenerator treeGenerator =this.hashMap.get(player);
        if (node) {
            Bukkit.broadcastMessage("Drawing nodes");
            treeGenerator.generateNodes();
            treeGenerator.drawNodes();
        }
        if (b) {
            do {
                Bukkit.broadcastMessage("Drawing gen");
            } while (treeGenerator.drawGen());
            this.hashMap.put(player, null);
        }
        Bukkit.broadcastMessage("Done");
    }
}
