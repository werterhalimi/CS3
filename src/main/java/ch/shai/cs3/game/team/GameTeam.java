package ch.shai.cs3.game.team;

import ch.shai.cs3.game.player.GamePlayer;
import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.List;

public abstract class GameTeam {
    protected List<GamePlayer> players;
    protected int size;
    protected String name;
    protected DyeColor color;
    protected Location spawn;

    public GameTeam(List<GamePlayer> players, String name, DyeColor color, Location spawn,int size) {
        this.players = players;
        this.name = name;
        this.color = color;
        this.spawn = spawn;
        this.size = size;
    }

    public String getName() {
        return name;
    }
    public DyeColor getColor() {
        return color;
    }

    public Location getSpawnPoint(){
        return this.spawn;
    }

    public void setSpawnPoint(Location spawnPoint){
        this.spawn = spawnPoint;
        for (GamePlayer player : this.players) {
            player.getBukkitPlayer().setRespawnLocation(this.spawn);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removePlayer(GamePlayer player){
        this.players.remove(player);
    }

    public void addPlayer(GamePlayer player){
        this.players.add(player);
        player.getBukkitPlayer().setRespawnLocation(this.spawn);
    }
    public boolean isFull(){

        return this.players.size() >= this.size;

    }

    public List<GamePlayer> getPlayers() {
        return players;
    }
}
