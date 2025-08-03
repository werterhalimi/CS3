package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class UnregisterPlayerLeave<T extends GamePlayer, U extends GameTeam>  extends GameStateRule<T, U> {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        this.getState().getGame().unRegisterPlayer(this.getState().getGame().getGamePlayer(event.getPlayer()));
    }
}
