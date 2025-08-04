package ch.shai.cs3.game.player;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.entity.Player;

public abstract class GamePlayer {
    protected Player player;
    protected Game<GamePlayer, GameTeam> game;
    protected boolean isPlaying;

    public GamePlayer(Player player, Game<GamePlayer, GameTeam> game) {
        this.player = player;
        this.game = game;
        this.isPlaying = true;
    }

    public Player getBukkitPlayer() {
        return player;
    }

    public GameTeam getTeam(){
        for (GameTeam team : this.game.getTeams()) {
            if (team.getPlayers().contains(this)) {
                return team;
            }
        }
        return null;
    }

    public void setTeam(GameTeam team){
        if (this.getTeam() != null) this.getTeam().removePlayer(this);
        team.addPlayer(this);
    }

    public void setPlaying(boolean newVal){
        this.isPlaying = newVal;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
