package ch.shai.cs3.game.team;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.utils.itemstack.ColorUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.List;

public abstract class GameTeam {
    protected List<GamePlayer> players;
    protected int size;
    protected String name;
    protected DyeColor color;
    protected NamedTextColor namedTextColor;
    protected Location spawn;

    public GameTeam(List<GamePlayer> players, String name, DyeColor color, Location spawn,int size) {
        this.players = players;
        this.name = name;
        this.color = color;
        this.spawn = spawn;
        this.size = size;
        this.namedTextColor = ColorUtils.dyeColorToChatColor(this.getColor());
    }

    public String getName() {
        return name;
    }
    public NamedTextColor getNamedTextColor() {
        return namedTextColor;
    }
    public DyeColor getColor() {
        return color;
    }
    public void setColor(DyeColor color) {
        this.color = color;
        this.namedTextColor = ColorUtils.dyeColorToChatColor(this.getColor());
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
        player.getBukkitPlayer().playerListName(Component.text(player.getBukkitPlayer().getName(), this.getNamedTextColor()));
    }
    public boolean isFull(){
        return this.players.size() >= this.size;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }
}
