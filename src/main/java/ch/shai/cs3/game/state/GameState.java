package ch.shai.cs3.game.state;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.state.rule.GameStateRule;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState {

    protected List<GameStateRule> rules;
    protected Game game;

    public GameState() {
        this.rules = new ArrayList<>();
    }

    public void loadRules(Game game){
        this.game = game;
        for (GameStateRule rule : this.rules) {
            rule.load(this);
        }
    }

    public void unLoadRules(){
        for (GameStateRule rule : this.rules) {
            rule.unLoad();
        }
    }


    public List<GameStateRule> getRules() {
        return rules;
    }

    public Game getGame() {
        return game;
    }
}
