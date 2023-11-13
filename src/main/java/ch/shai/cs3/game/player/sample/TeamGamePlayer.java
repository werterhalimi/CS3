package ch.shai.cs3.game.player.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.entity.Player;

public class TeamGamePlayer extends GamePlayer {

    private final GameTeam team;
    public TeamGamePlayer(Player player, GameTeam team) {
        super(player);
        this.team = team;
    }

    public GameTeam getTeam() {
        return team;
    }
}
