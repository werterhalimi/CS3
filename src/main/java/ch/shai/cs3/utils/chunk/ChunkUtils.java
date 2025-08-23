package ch.shai.cs3.utils.chunk;

import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.ArrayList;
import java.util.List;

public class ChunkUtils {
    /**
     * Compute the location of the chunks in a WorldBorder
     *
     * @param world world
     * @return List of chunk's Xs and Ys {chunkX, chunkZ}
     */
    public static List<int[]> getOneBlockPerChunkInWorldBorder(World world) {
        List<int[]> chunks = new ArrayList<>();
        WorldBorder wb = world.getWorldBorder();
        double centerX = wb.getCenter().getX();
        double centerZ = wb.getCenter().getZ();
        double size = wb.getSize();
        int halfChunks = (int) (size / 2 / 16);

        int minChunkX = (int) (centerX / 16) - halfChunks;
        int maxChunkX = (int) (centerX / 16) + halfChunks;
        int minChunkZ = (int) (centerZ / 16) - halfChunks;
        int maxChunkZ = (int) (centerZ / 16) + halfChunks;

        for (int cx = minChunkX; cx <= maxChunkX; cx++) {
            for (int cz = minChunkZ; cz <= maxChunkZ; cz++) {
                int[] array = new int[2];
                array[0] = cx;
                array[1] = cz;
                chunks.add(array);
            }
        }
        return chunks;
    }


}
