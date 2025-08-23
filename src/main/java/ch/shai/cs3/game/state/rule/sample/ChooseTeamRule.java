package ch.shai.cs3.game.state.rule.sample;

import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.game.team.GameTeam;
import ch.shai.cs3.utils.itemstack.WhoolUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.text.Component;

public class ChooseTeamRule<T extends GamePlayer, U extends GameTeam> extends GameStateRule<T,U> {

    private boolean preventStartWhenTeamAreFull = false;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        GameTeam gameTeam = this.getState().getGame().getTeamByName(hand.getItemMeta().getDisplayName());
        if (gameTeam != null) {
            GamePlayer gamePlayer = this.getState().getGame().getGamePlayer(player);
            gamePlayer.setTeam(gameTeam);

            gamePlayer.sendMessage(Component.text("Vous avez bien rejoint la team + ").append(Component.text(gameTeam.getName(), gameTeam.getNamedTextColor())));
            if (!this.preventStartWhenTeamAreFull) { // TODO debounce
                for (GameTeam team : this.getState().getGame().getTeams()) {
                    if (!team.isFull())
                        return;
                }
                this.getState().getGame().setState(this.getState().getGame().getNextState());
            }
        }
    }

    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        for (GameTeam team : this.getState().getGame().getTeams()) {
            player.getInventory().addItem(WhoolUtils.getColoredWool(team.getColor(), team.getName()));
        }
    }


    public ChooseTeamRule<T, U> setPreventStartWhenTeamAreFull(boolean newValue){
        this.preventStartWhenTeamAreFull = newValue;
        return this;
    }
}
