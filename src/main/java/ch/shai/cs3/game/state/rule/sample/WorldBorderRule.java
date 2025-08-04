package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;

public class WorldBorderRule<T extends GamePlayer,U extends GameTeam> extends GameStateRule<T,U> {

    private int size;

    public WorldBorderRule(int size){
        this.size = size;
    }

    @Override
    public void onLoad() {
        WorldBorder border = this.getState().getGame().getWorld().getWorldBorder();
        border.setCenter(this.getState().getGame().getWorld().getSpawnLocation());
        border.setSize(this.size);
        border.setDamageAmount(0.2);
        border.setDamageBuffer(5.0);
        border.setWarningDistance(10);
        border.setWarningTime(15);
    }
}
