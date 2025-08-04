package ch.shai.cs3.utils.portal;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PortalUtils {


    public static Location getEndPortalCenter(Block portalBlock) {
        if (portalBlock.getType() != Material.END_PORTAL) {
            throw new IllegalArgumentException("Block must be an END_PORTAL block");
        }

        World world = portalBlock.getWorld();
        int baseY = portalBlock.getY();

        // Recherche de tous les blocs END_PORTAL voisins (on suppose un portail 3x3 max)
        Set<Block> portalBlocks = new HashSet<>();
        Queue<Block> toCheck = new LinkedList<>();
        toCheck.add(portalBlock);

        while (!toCheck.isEmpty()) {
            Block current = toCheck.poll();
            if (current.getType() == Material.END_PORTAL && !portalBlocks.contains(current)) {
                portalBlocks.add(current);
                for (BlockFace face : BlockFace.values()) {
                    if (face == BlockFace.UP || face == BlockFace.DOWN) continue; // On reste en 2D
                    Block neighbor = current.getRelative(face);
                    if (neighbor.getType() == Material.END_PORTAL) {
                        toCheck.add(neighbor);
                    }
                }
            }
        }

        // Calcul du centre du portail
        int minX = portalBlocks.stream().mapToInt(b -> b.getX()).min().orElse(portalBlock.getX());
        int maxX = portalBlocks.stream().mapToInt(b -> b.getX()).max().orElse(portalBlock.getX());
        int minZ = portalBlocks.stream().mapToInt(b -> b.getZ()).min().orElse(portalBlock.getZ());
        int maxZ = portalBlocks.stream().mapToInt(b -> b.getZ()).max().orElse(portalBlock.getZ());

        double centerX = (minX + maxX) / 2.0 + 0.5;
        double centerY = baseY + 0.5;
        double centerZ = (minZ + maxZ) / 2.0 + 0.5;

        return new Location(world, centerX, centerY, centerZ);
    }

}
