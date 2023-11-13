package ch.shai.cs3.game.team;

import ch.shai.cs3.game.player.GamePlayer;

import java.util.List;

public abstract class GameTeam {
    protected List<GamePlayer> players;
    protected String name;

    public GameTeam(List<GamePlayer> players, String name) {
        this.players = players;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }
}
