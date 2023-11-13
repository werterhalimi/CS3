package ch.shai.cs3.game.player;

import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.entity.Player;

public abstract class GamePlayer {
    protected Player player;

    public GamePlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
