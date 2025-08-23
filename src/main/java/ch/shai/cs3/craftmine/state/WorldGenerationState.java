package ch.shai.cs3.craftmine.state;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.rule.LoadAllWorldBorderRule;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.game.state.rule.sample.PlayerCannotJoinRule;
import ch.shai.cs3.game.state.rule.sample.WorldBorderRule;

public class WorldGenerationState extends GameState<CraftMineGamePlayer, CraftMineGameTeam> {
    public WorldGenerationState() {

        this.addRule(new WorldBorderRule<CraftMineGamePlayer, CraftMineGameTeam>(CraftMineGameState.WORLD_BORDER_SIZE));
        this.addRule(new PlayerCannotJoinRule<>("Generation du monde"));
        this.addRule(new LoadAllWorldBorderRule());
    }



}
