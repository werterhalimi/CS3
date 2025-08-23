package ch.shai.cs3.craftmine.player;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.player.GamePlayer;
import org.bukkit.entity.Player;

public class CraftMineGamePlayer extends GamePlayer {
    private double inventoryValue;

    public CraftMineGamePlayer(Player player, Game game) {
        super(player, game);

        this.inventoryValue = 0;
    }

    public void setInventoryValue(double inventoryValue) {
        this.inventoryValue = inventoryValue;
        this.player.sendActionBar("Valeur de l'inventaire: " + this.inventoryValue);
        this.sendMessage("Valeur de l'inventaire: " + this.inventoryValue);
    }
}
