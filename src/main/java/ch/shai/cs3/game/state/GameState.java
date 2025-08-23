package ch.shai.cs3.game.state;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState<T extends GamePlayer, U extends GameTeam> {

    protected List<GameStateRule<T,U>> rules;

    protected Game<T, U> game;

    public GameState() {
        this.rules = new ArrayList<>();
    }

    public GameState<T, U> addRule(GameStateRule<T,U> rule){
        this.rules.add(rule);
        return this;
    }

    public void loadRules(Game<T, U> game){
        this.game = game;
        for (GameStateRule<T,U> rule : this.rules) {
            rule.load(this);
        }
        this.onLoad();
    }

    public void unLoadRules(){
        for (GameStateRule<T,U> rule : this.rules) {
            rule.disable();
        }
        this.onUnLoad();
    }

    public void onLoad(){}
    public void onUnLoad(){}


    public List<GameStateRule<T,U>> getRules() {
        return this.rules;
    }

    public Game<T, U> getGame() {
        return game;
    }


}
