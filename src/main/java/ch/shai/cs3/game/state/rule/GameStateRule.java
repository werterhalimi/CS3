package ch.shai.cs3.game.state.rule;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class GameStateRule<T extends GamePlayer, U extends GameTeam> implements Listener {
    protected GameState<T,U> state;
    private List<GameStateRule<T, U>> dependencies = new ArrayList<>();

    public void disable(){
        this.getState().getGame().unRegisterEvent(this);
        for (GameStateRule<T, U> dependency : this.dependencies) {
            dependency.disable();
        }
        this.onDisable();
    }

    public void load(GameState<T, U> state){
        this.state = state;
        this.getState().getGame().registerEvent(this);
        this.onLoad();
    };

    protected void addDependencies(GameStateRule<T,U> dep){
        this.dependencies.add(dep);
        dep.load(this.state);
    }

    public void onLoad() {}

    public void onDisable() {}

    public GameState<T,U> getState() {
        return state;
    }
}
