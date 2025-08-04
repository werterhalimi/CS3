package ch.shai.cs3.game;

import ch.shai.cs3.commands.StartCommand;
import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class Game<
        T extends GamePlayer,
        U extends GameTeam
        > {

    protected GameState<T,U> currentState;
    protected List<GameState<T,U>> states;
    protected List<U> teams;
    protected List<T> players;
    protected World world;
    private JavaPlugin plugin;



    public Game(JavaPlugin plugin, GameState<T,U> currentState) {
        this.plugin = plugin;
        this.currentState = currentState;
        this.states = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.players = new ArrayList<>();
        this.states.add(this.currentState);
        this.world = Bukkit.getWorlds().get(0);
    }

    public Game(JavaPlugin plugin) {
        this.plugin = plugin;
        this.states = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.players = new ArrayList<>();
        this.world = Bukkit.getWorlds().get(0);
        Bukkit.getPluginCommand("start").setExecutor(new StartCommand<T,U>(this));
    }

    public void enable(){
        this.setState(this.states.get(0));
    }

    public void endCurrentState(){
        this.setState(this.getNextState());
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

    public abstract T registerPlayer(Player player);

    public abstract void unRegisterPlayer(T player);

    public void loadCurrentStep(){
        this.currentState.loadRules(this);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public List<GameState<T, U>> getStates() {
        return states;
    }

    public GameState<T, U> getNextState(){
        int index = this.states.indexOf(this.currentState);
        if (index != -1 && index != this.states.size()) // todo throw error
            return this.states.get(index + 1);
        return this.getStates().get(0);
    }

    public List<U> getTeams() {
        return teams;
    }

    public U getTeamByName(String teamName){
        for (U team : this.teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    public T getGamePlayer(Player player){
        for (T gamePlayer : this.getPlayers()) {
            if (gamePlayer.getBukkitPlayer().equals(player))
                return gamePlayer;
        }
        return null;
    }

    public List<T> getPlayers() {
        return players;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public GameState<T,U> getCurrentState() {
        return currentState;
    }

    public void setState(GameState<T,U> currentState) {
        if (this.currentState != null) this.currentState.unLoadRules();
        this.currentState = currentState;
        this.loadCurrentStep();
    }
}
