package ch.shai.cs3.game.state.rule;

import ch.shai.cs3.game.state.GameState;

public abstract class GameStateRule {
    protected GameState state;


    public void load(GameState state){
        this.state = state;
        this.onLoad();
    };
    public abstract void onLoad();

    public abstract void unLoad();

    public GameState getState() {
        return state;
    }


}
