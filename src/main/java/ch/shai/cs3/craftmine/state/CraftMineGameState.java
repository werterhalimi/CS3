package ch.shai.cs3.craftmine.state;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.rule.*;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.state.rule.sample.PlayerRespawnTeamSpawnPointRule;
import ch.shai.cs3.game.state.rule.sample.WorldBorderRule;

public class CraftMineGameState extends GameState<CraftMineGamePlayer, CraftMineGameTeam> {
    public final static int WORLD_BORDER_SIZE = 1000;
    public CraftMineGameState(){
        this.rules.add(new MurderHealthPerturbationRule());
        this.rules.add(new NoMoveAtStartRule());
        this.rules.add(new ItemSpawnWithGradeRule());
        this.rules.add(new UpdateValueOnInventoryEventRule());
        this.rules.add(new WorldBorderRule<CraftMineGamePlayer, CraftMineGameTeam>(CraftMineGameState.WORLD_BORDER_SIZE));
        this.rules.add(new TeamCanLeaveRule());
        this.rules.add(new PlayerRespawnTeamSpawnPointRule<CraftMineGamePlayer, CraftMineGameTeam>());
    }

    @Override
    public void onLoad() {
        for (CraftMineGamePlayer player : this.game.getPlayers()) {
            player.getBukkitPlayer().teleport(player.getTeam().getSpawnPoint());
            player.getBukkitPlayer().getInventory().clear();
        }
    }
}
