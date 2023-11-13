package ch.shai.cs3.turbine;

import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.state.rule.sample.NoBlockRule;
import ch.shai.cs3.game.state.rule.sample.NoDamageRule;

import java.util.List;

public class WaitingForPlayerState extends GameState {
    public WaitingForPlayerState() {
        this.rules.add(new WaitingForPlayerRule());
        this.rules.add(new NoDamageRule());
        this.rules.add(new NoBlockRule());
    }
}
