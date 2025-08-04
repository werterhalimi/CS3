package ch.shai.cs3.craftmine.state;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.rule.ItemSpawnWithGradeRule;
import ch.shai.cs3.craftmine.rule.MurderHealthPerturbationRule;
import ch.shai.cs3.craftmine.rule.NoMoveAtStartRule;
import ch.shai.cs3.craftmine.rule.UpdateValueOnInventoryEventRule;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.GameState;
import org.bukkit.Bukkit;

public class CraftMineGameState extends GameState<CraftMineGamePlayer, CraftMineGameTeam> {
    public CraftMineGameState(){
        this.rules.add(new MurderHealthPerturbationRule());
        this.rules.add(new NoMoveAtStartRule());
        this.rules.add(new ItemSpawnWithGradeRule());
        this.rules.add(new UpdateValueOnInventoryEventRule());
    }

    @Override
    public void onLoad() {
        for (CraftMineGamePlayer player : this.game.getPlayers()) {
            player.getBukkitPlayer().teleport(player.getTeam().getSpawnPoint());
        }
    }
}
