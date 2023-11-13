package ch.shai.cs3.game;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

    protected GameState currentState;
    protected List<GameState> states;
    protected List<GameTeam> teams;
    protected List<GamePlayer> players;
    private JavaPlugin plugin;


    public Game(JavaPlugin plugin, GameState currentState) {
        this.plugin = plugin;
        this.currentState = currentState;
        this.states = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.players = new ArrayList<>();
        this.states.add(this.currentState);
    }

    public void addState(GameState state){
        this.states.add(state);
    }

    public void registerEvent(Listener listener){
        Bukkit.getServer().getPluginManager().registerEvents(listener, this.getPlugin());
    }

    public void unRegisterEvent(Listener listener){
        HandlerList.unregisterAll(listener);
    }

    public abstract GamePlayer registerPlayer(Player player);
    public abstract void unRegisterPlayer(Player player);


    public void loadCurrentStep(){
        this.currentState.loadRules(this);
    }


    public JavaPlugin getPlugin() {
        return plugin;
    }

    public List<GameState> getStates() {
        return states;
    }

    public List<GameTeam> getTeams() {
        return teams;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setState(GameState currentState) {
        this.currentState = currentState;
        this.loadCurrentStep();
    }


}
