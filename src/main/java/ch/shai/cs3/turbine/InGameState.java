package ch.shai.cs3.turbine;

import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.state.rule.sample.NoBlockRule;
import ch.shai.cs3.game.state.rule.sample.NoDamageRule;

public class InGameState extends GameState {
    public InGameState() {
        this.rules.add(new DestroyBlockRule());
        this.rules.add(new NoBlockRule());
        this.rules.add(new NoDamageRule());
    }
}
