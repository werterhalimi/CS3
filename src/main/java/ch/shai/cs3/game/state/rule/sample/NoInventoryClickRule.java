package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NoInventoryClickRule<T extends GamePlayer, U extends GameTeam> extends GameStateRule<T,U> {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        event.setCancelled(true);
    }
}
