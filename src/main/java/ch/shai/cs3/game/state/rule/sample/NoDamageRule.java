package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.state.rule.GameStateRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoDamageRule extends GameStateRule implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
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
