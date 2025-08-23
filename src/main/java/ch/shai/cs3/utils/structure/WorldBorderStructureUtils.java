package ch.shai.cs3.utils.structure;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldBorderStructureUtils {
    public static List<Location> getEquidistantPointsInBorder(World world, int n) {
        List<Location> locations = new ArrayList<>();
        Random random = new Random();

        WorldBorder border = world.getWorldBorder();
        double size = border.getSize() - 10; // marge de sécurité
        Location center = border.getCenter();
        double half = size / 2.0;

        int attempts = 0;
        while (locations.size() < n && attempts < n * 1000) { // limite pour éviter boucle infinie
            attempts++;

            // Générer coord aléatoire dans la zone
            double x = center.getX() + (random.nextDouble() * 2 - 1) * half;
            double z = center.getZ() + (random.nextDouble() * 2 - 1) * half;

            // Trouver la hauteur de la surface
            int y = world.getHighestBlockYAt((int) x, (int) z);
            Location candidate = new Location(world, x, y, z);

            // Vérifier distance minimale par rapport aux autres
            boolean valid = true;
            for (Location loc : locations) {
                if (loc.distance(candidate) < size / n) { // critère d'éloignement
                    valid = false;
                    break;
                }
            }

            if (valid) {
                locations.add(candidate.add(0.5, 1, 0.5)); // centrer et mettre au-dessus du bloc
            }
        }

        return locations;
    }
}
