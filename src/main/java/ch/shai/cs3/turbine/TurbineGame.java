package ch.shai.cs3.turbine;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.player.sample.BasicGamePlayer;
import ch.shai.cs3.game.state.GameState;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TurbineGame extends Game {

    private List<GamePlayer> alivePlayers;

    public TurbineGame(JavaPlugin plugin, GameState currentState) {
        super(plugin , currentState);
        this.alivePlayers = new ArrayList<>();
    }

    @Override
    public GamePlayer registerPlayer(Player player) {
        BasicGamePlayer basicGamePlayer = new BasicGamePlayer(player);
        this.players.add(basicGamePlayer);
        this.alivePlayers.add(basicGamePlayer);
        return basicGamePlayer;
    }

    public void playerDie(GamePlayer player){
        this.alivePlayers.remove(player);
    }

    public List<GamePlayer> getAlivePlayers() {
        return alivePlayers;
    }

    @Override
    public void unRegisterPlayer(Player player) {
        GamePlayer gamePlayer = null;
        for (GamePlayer p : this.players) {
            if (p.getPlayer() == player) {
                gamePlayer = p;
                break;
            }
        }
        if (gamePlayer != null) this.players.remove(gamePlayer);
    }
}
