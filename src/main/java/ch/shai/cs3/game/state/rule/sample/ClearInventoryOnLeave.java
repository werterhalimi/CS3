package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class ClearInventoryOnLeave<T extends GamePlayer, U extends GameTeam> extends GameStateRule<T,U> {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        event.getPlayer().getInventory().clear();
        T player = this.getState().getGame().getGamePlayer(event.getPlayer());
        if (player != null) {
            this.getState().getGame().unRegisterPlayer(player);
        }
    }
}
