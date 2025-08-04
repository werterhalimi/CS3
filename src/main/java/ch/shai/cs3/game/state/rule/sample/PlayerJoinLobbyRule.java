package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinLobbyRule<T extends GamePlayer, U extends GameTeam> extends GameStateRule<T,U> {

    public boolean shouldStartWhenEnoughPlayer = false;
    public int minimumPlayerNeeded = 1;

    private Location spawn;

    public PlayerJoinLobbyRule(Location spawn){
        this.spawn = spawn;
    }


    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent event){
        GamePlayer player = this.getState().getGame().registerPlayer(event.getPlayer());
        player.getBukkitPlayer().teleport(this.spawn);
        if (this.getState().getGame().getPlayers().size() >= this.minimumPlayerNeeded && this.shouldStartWhenEnoughPlayer) {
            this.getState().getGame().endCurrentState();
        }
    }


    public PlayerJoinLobbyRule<T, U> setShouldStartWhenEnoughPlayer(boolean newValue) {
        this.shouldStartWhenEnoughPlayer = newValue;
        return this;
    }
    public PlayerJoinLobbyRule<T, U> setMinimumPlayerNeeded(int newValue) {
        this.minimumPlayerNeeded = newValue;
        return this;
    }
}
