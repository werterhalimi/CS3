package ch.shai.cs3.turbine;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.player.GamePlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class DestroyRunnable extends BukkitRunnable {

    private TurbineGame game;

    public DestroyRunnable(TurbineGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        for (GamePlayer alivePlayer : this.game.getAlivePlayers()) {
            Block block = alivePlayer.getPlayer().getLocation().subtract(0, 1, 0).getBlock();
            if (block.getType() != Material.AIR){
                Material material = block.getType();
                if (material == Material.STONE) block.setType(Material.COBBLESTONE);
                else if (material == Material.COBBLESTONE) block.setType(Material.IRON_BLOCK);
                else if (material == Material.IRON_BLOCK) block.setType(Material.DIRT);
                else if (material == Material.DIRT) block.setType(Material.AIR);
            }
        }
        if (this.game.getAlivePlayers().isEmpty()) this.cancel();
    }
}
