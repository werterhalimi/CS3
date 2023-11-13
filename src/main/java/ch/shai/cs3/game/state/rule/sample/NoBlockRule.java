package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.state.rule.GameStateRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoBlockRule extends GameStateRule implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }

    @Override
    public void onLoad() {
        this.getState().getGame().registerEvent(this);
    }

    @Override
    public void unLoad() {
        this.getState().getGame().unRegisterEvent(this);
    }
}
