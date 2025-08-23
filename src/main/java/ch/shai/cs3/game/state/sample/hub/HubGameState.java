package ch.shai.cs3.game.state.sample.hub;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.state.rule.sample.*;
import ch.shai.cs3.game.team.GameTeam;
import ch.shai.cs3.utils.structure.SquareStructure;
import org.bukkit.Location;
import org.bukkit.Material;


public class HubGameState<T extends GamePlayer,U extends GameTeam> extends GameState<T,U> {

    private Location spawn;
    private HubGameStateOption options;

    public HubGameState(Location spawn, HubGameStateOption options){
        this.spawn = spawn;
        this.options = options;
        this.addRule(new NoBlockRule<T,U>());
        this.addRule(new NoDamageRule<T,U>());
        this.addRule(new NoStarvationRule<T,U>());
        this.addRule(new ChooseTeamRule<T,U>());
        this.addRule(new PreventDaylightCycleRule<T,U>());
        this.addRule(new ClearInventoryOnLeave<T,U>());
        this.addRule(new UnregisterPlayerLeave<T,U>());
        this.addRule(new NoInventoryClickRule<>());
        this.addRule(new PlayerJoinLobbyRule<T,U>(this.spawn)
                .setShouldStartWhenEnoughPlayer(this.options.shouldAutoStartWhenEnoughPlayer)
                .setMinimumPlayerNeeded(this.options.numberOfPlayerToStart));
    }

    @Override
    public void onLoad() {
        if (!this.options.preventLobbyStructureGeneration) {
            int y = (this.spawn.getBlockY() - 2);
            SquareStructure.createEmptyCube(
                    new Location(this.getGame().getWorld(), this.spawn.getBlockX() - 15,y,this.spawn.getBlockZ() - 15),
                    new Location(this.getGame().getWorld(), this.spawn.getBlockX() + 15,y + 5,this.spawn.getBlockZ() + 15),
                    Material.GLASS
            );
        }
    }
}
