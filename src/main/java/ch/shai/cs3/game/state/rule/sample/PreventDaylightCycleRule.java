package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.GameRule;

public class PreventDaylightCycleRule<T extends GamePlayer, U extends GameTeam> extends GameStateRule<T,U> {
    @Override
    public void onLoad() {
        this.getState().getGame().getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
    }

    @Override
    public void onDisable() {
        this.getState().getGame().getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
    }
}
