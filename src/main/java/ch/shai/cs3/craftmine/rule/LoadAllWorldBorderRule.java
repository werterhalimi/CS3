package ch.shai.cs3.craftmine.rule;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.utils.math.MathUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ch.shai.cs3.utils.chunk.ChunkUtils.getOneBlockPerChunkInWorldBorder;

public class LoadAllWorldBorderRule extends GameStateRule<CraftMineGamePlayer, CraftMineGameTeam> {
    @Override
    public void onLoad() {
        ChunkGeneratorRunnable runnable = new ChunkGeneratorRunnable(this.getState().getGame().getWorld(), 10, () -> {
            this.getState().getGame().endCurrentState();
        });
        runnable.runTaskTimer(this.getState().getGame().getPlugin(), 1L, 1L);
    }



    private static class ChunkGeneratorRunnable extends BukkitRunnable {
        private final World world;
        private final int chunksPerTickLimit;
        private final int minChunkX;
        private final int maxChunkX;
        private final int minChunkZ;
        private final int maxChunkZ;
        private final int totalChunks;
        private final AtomicInteger remaining;
        private int currentChunkX;
        private int currentChunkZ;
        private int broadcastIndex = 0;
        private Runnable resolveCallback;
        private final ConcurrentHashMap<Material, Long> blockCounts = new ConcurrentHashMap<>();
        private final List<Integer> broadcastIndices;
        private long startTime;

        public ChunkGeneratorRunnable(World world, int chunksPerTickLimit) {
            this(world, chunksPerTickLimit, null);
        }

        public ChunkGeneratorRunnable(World world, int chunksPerTickLimit, Runnable resolveCallback) {
            this.world = world;
            this.startTime = System.currentTimeMillis();
            this.chunksPerTickLimit = chunksPerTickLimit;
            this.resolveCallback = resolveCallback;

            double centerX = world.getWorldBorder().getCenter().getX();
            double centerZ = world.getWorldBorder().getCenter().getZ();
            int halfChunks = (int) (world.getWorldBorder().getSize() / 2 / 16);

            this.minChunkX = (int) (centerX / 16) - halfChunks;
            this.maxChunkX = (int) (centerX / 16) + halfChunks;
            this.minChunkZ = (int) (centerZ / 16) - halfChunks;
            this.maxChunkZ = (int) (centerZ / 16) + halfChunks;

            this.currentChunkX = this.minChunkX;
            this.currentChunkZ = this.minChunkZ;

            this.totalChunks = (this.maxChunkX - this.minChunkX + 1) * (this.maxChunkZ - this.minChunkZ + 1);
            this.remaining = new AtomicInteger(this.totalChunks);
            this.broadcastIndices = MathUtils.getStepIndexes(this.totalChunks, 10);
        }

        @Override
        public void run() {
            int count = 0;

            while (count < this.chunksPerTickLimit && this.currentChunkZ <= this.maxChunkZ) {
                int cx = this.currentChunkX;
                int cz = this.currentChunkZ;

                this.currentChunkX++;
                if (this.currentChunkX > this.maxChunkX) {
                    this.currentChunkX = this.minChunkX;
                    this.currentChunkZ++;
                }

                this.world.getChunkAtAsync(cx, cz, true, chunk -> {
                    this.countBlocksInChunk(chunk);

                    int left = this.remaining.decrementAndGet();
                    int completed = this.totalChunks - left;

                    if (this.broadcastIndex < this.broadcastIndices.size() &&
                            completed >= this.broadcastIndices.get(this.broadcastIndex)) {
                        int percent = (this.broadcastIndex + 1) * 10;
                        Bukkit.broadcast(Component.text("Génération + comptage : " + percent + "% terminé !"));
                        this.broadcastIndex++;
                    }

                    if (left == 0) {
                        Bukkit.broadcast(Component.text("Tous les chunks générés et comptés !"));
                        long elapsed = System.currentTimeMillis() - this.startTime;

                        // Calculer le total de blocks non-air
                        long totalBlocks = this.blockCounts.values().stream().mapToLong(Long::longValue).sum();
                        double totalPercent = 0;

                        if (totalBlocks == 0) return;

                        List<Map.Entry<Material, Long>> sortedEntries = new ArrayList<>(this.blockCounts.entrySet());
                        sortedEntries.sort((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())); // tri décroissant

                        // Parcourir la liste triée
                        for (Map.Entry<Material, Long> entry : sortedEntries) {
                            double percent = (double) entry.getValue() / totalBlocks * 100;
                            totalPercent += percent;
                            Bukkit.broadcast(Component.text(entry.getKey().name() + " : " + String.format("%.2f", percent) + "%"));
                        }

                        // Afficher le total des pourcentages
                        Bukkit.broadcast(Component.text("Total des pourcentages : " + String.format("%.2f", totalPercent) + "%"));

                        // Afficher le temps total
                        Bukkit.broadcast(Component.text("Temps total : " + (elapsed / 1000.0) + " secondes"));

                        if (this.resolveCallback != null) this.resolveCallback.run();
                        this.cancel();
                    }
                });

                count++;
            }
        }

        private void countBlocksInChunk(Chunk chunk) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = this.world.getMinHeight(); y < this.world.getMaxHeight(); y++) {
                        Material mat = chunk.getBlock(x, y, z).getType();
                        if (mat != Material.AIR) {
                            this.blockCounts.merge(mat, 1L, Long::sum);
                        }
                    }
                }
            }
        }
    }

}
