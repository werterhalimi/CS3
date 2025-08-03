package ch.shai.cs3.utils.structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;


public class SquareStructure {


    public static void createEmptyCube(Location loc1, Location loc2, Material type){
        World world = loc1.getWorld();
        if (world == null || !world.equals(loc2.getWorld())) {
            throw new IllegalArgumentException("Locations must be in the same world");
        }


        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        // Faces Z = minZ and maxZ
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                world.getBlockAt(x, y, minZ).setType(type, false);
                world.getBlockAt(x, y, maxZ).setType(type, false);
            }
        }

        // Faces Y = minY and maxY (avoid double-setting edges)
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ + 1; z < maxZ; z++) {
                world.getBlockAt(x, minY, z).setType(type, false);
                world.getBlockAt(x, maxY, z).setType(type, false);
            }
        }

        // Faces X = minX and maxX (avoid double-setting edges)
        for (int y = minY + 1; y < maxY; y++) {
            for (int z = minZ + 1; z < maxZ; z++) {
                world.getBlockAt(minX, y, z).setType(type, false);
                world.getBlockAt(maxX, y, z).setType(type, false);
            }
        }
    }
}
