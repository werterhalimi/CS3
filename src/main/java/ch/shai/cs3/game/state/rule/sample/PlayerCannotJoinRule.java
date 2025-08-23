package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerCannotJoinRule<T extends GamePlayer,U extends GameTeam> extends GameStateRule<T,U> {
    private final String joinMessage;


    public PlayerCannotJoinRule(String joinMessage){
        this.joinMessage = joinMessage;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().kick(Component.text(this.joinMessage));
    }
}
