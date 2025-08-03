package ch.shai.cs3.craftmine.rule;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.rule.GameStateRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemSpawnWithGradeRule extends GameStateRule<CraftMineGamePlayer, CraftMineGameTeam> {
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event){
        ItemMeta meta = event.getEntity().getItemStack().getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("5");
        meta.setLore(lore);
        event.getEntity().getItemStack().setItemMeta(meta);
    }
}
