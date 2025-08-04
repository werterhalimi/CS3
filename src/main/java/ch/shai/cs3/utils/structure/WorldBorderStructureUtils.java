package ch.shai.cs3.utils.structure;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.ArrayList;
import java.util.List;

public class WorldBorderStructureUtils {
    public static List<Location> getEquidistantPointsInBorder(World world, int n) {
        List<Location> points = new ArrayList<>();
        if (world == null || n <= 0) {
            return points; // Retourne une liste vide si les paramètres sont invalides
        }

        // Récupère la bordure du monde
        WorldBorder border = world.getWorldBorder();
        double centerX = border.getCenter().getX();
        double centerZ = border.getCenter().getZ();
        double size = border.getSize(); // Diamètre de la bordure
        double radius = (size / 2) * 0.97; // Réduit de 3% pour la marge

        // Calcule les angles pour une répartition circulaire
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n; // Angle en radians pour chaque point
            double x = centerX + radius * Math.cos(angle); // Coordonnée X
            double z = centerZ + radius * Math.sin(angle); // Coordonnée Z
            points.add(new Location(world, x, world.getHighestBlockYAt((int) x ,(int) z), z));
        }

        return points;
    }
}
