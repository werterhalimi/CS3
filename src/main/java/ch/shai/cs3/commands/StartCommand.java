package ch.shai.cs3.commands;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class StartCommand<T extends GamePlayer, U extends GameTeam> extends CSCommand{
    private Game<T,U> game;
    public StartCommand(Game<T,U> game){
        this.game = game;
        this.setAdmin(true);
    }
    @Override
    protected void execute(Player player, Location location, World world, String label) {
        this.game.endCurrentState();
    }
}
