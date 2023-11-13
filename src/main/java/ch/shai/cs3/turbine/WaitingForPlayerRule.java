package ch.shai.cs3.turbine;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class WaitingForPlayerRule extends GameStateRule implements Listener {

    Location spawn = new Location(Bukkit.getWorld("world"), -103, 161, 282);

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent event){
        GamePlayer player = this.getState().getGame().registerPlayer(event.getPlayer());
        player.getPlayer().teleport(spawn);
        player.getPlayer().setGameMode(GameMode.SURVIVAL);
        player.getPlayer().setStarvationRate(0);
        if (!this.getState().getGame().getPlayers().isEmpty()){
            Bukkit.broadcastMessage("La partie va commencer");
            this.getState().getGame().setState(this.getState().getGame().getStates().get(1));
        }
        else {
            player.getPlayer().sendMessage("La partie commencera a 3 joueurs");
        }
        event.setJoinMessage("Un joueur vient de rejoindre la parite !");
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        this.getState().getGame().unRegisterPlayer(event.getPlayer());
    }

    @Override
    public void onLoad() {
        System.out.println("ICI");
        this.getState().getGame().registerEvent(this);
    }

    @Override
    public void unLoad() {
        this.getState().getGame().unRegisterEvent(this);
    }
}
