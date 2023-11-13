package ch.shai.cs3.utils.gif;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GifRunnable extends BukkitRunnable {

    private int frame;
    private int height, width;
    private Location base;
    private Player player;
    private Color[][][] pixels;
    private double blockX, blockY;
    private double xTmp;

    public GifRunnable(int height, int width, Location base, Player player, int[][] pixels, double xTmp) {
        this.height = height;
        this.width = width;
        this.base = base;
        this.player = player;
        this.xTmp = xTmp;
        this.frame = 1;
        this.blockX = 0.8;
        this.blockY = 0.8;
        Color[][][] tmp = new Color[pixels.length][][];
        int f = 0;
        while (f < 1){
            tmp[f] = new Color[width][];
            for (int x = 0; x < width; x++) {
                tmp[f][x] = new Color[height];
                for (int y = 0; y < height; y++) {
                    tmp[f][x][y] = Color.fromARGB(pixels[f+1][ y * width + x]);
                }
            }
            f++;
        }
        System.out.println("finito");
        this.frame = 0;
        this.pixels = tmp;
    }

    private void iteration(){
        this.frame++;
        if (frame >= 1) frame = 0;
    }


    @Override
    public void run() {
        Location loc = base.clone();
        for (int y = 0; y < height; y+=7) {
            loc.setX(xTmp);
            for (int x = 0; x < width; x+=7) {
                System.out.println(this.frame);
                Color pixel = this.pixels[frame][x][y];
                iteration();
                player.spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 1, new Particle.DustTransition(pixel,this.pixels[frame][x][y],4.0f));
                loc = loc.add(blockX,0,0);
            }
            loc = loc.add(0,blockY,0);
        }
        iteration();
        base.add(0,0,1);
    }
}
