package ch.shai.cs3.craftmine.rule;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.utils.inventory.InventoryValueUtils;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryEvent;

public class UpdateValueOnInventoryEventRule extends GameStateRule<CraftMineGamePlayer, CraftMineGameTeam> {
    @EventHandler
    public void onInventory(InventoryEvent event){
        for (HumanEntity viewer : event.getInventory().getViewers()) {
            Player bukkitPlayer = (Player) viewer;
            CraftMineGamePlayer player = this.getState().getGame().getGamePlayer(bukkitPlayer);
            double value = InventoryValueUtils.gradePlayerInventory(player.getBukkitPlayer());
            player.setInventoryValue(value);
        }
    }
}
