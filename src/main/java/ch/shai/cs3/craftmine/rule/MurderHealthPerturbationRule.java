package ch.shai.cs3.craftmine.rule;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.rule.GameStateRule;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;


public class MurderHealthPerturbationRule extends GameStateRule<CraftMineGamePlayer, CraftMineGameTeam> {

    public static int PLAYER_MAX_HEALTH = 40;
    public static int PLAYER_MIN_HEALTH = 2;

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent event){
        Entity source = event.getDamageSource().getCausingEntity();
        if (source instanceof Player){
            this.incrementPlayerMaxHealth(event.getPlayer());
            this.decrementPlayerMaxHealth(((Player) source));

        }
    }

    private void incrementPlayerMaxHealth(Player player){
        AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.MAX_HEALTH);
        maxHealthAttribute.setBaseValue(Math.min(MurderHealthPerturbationRule.PLAYER_MAX_HEALTH,maxHealthAttribute.getBaseValue() + 2));
    }
    private void decrementPlayerMaxHealth(Player player){
        AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.MAX_HEALTH);
        maxHealthAttribute.setBaseValue(Math.max(MurderHealthPerturbationRule.PLAYER_MIN_HEALTH,maxHealthAttribute.getBaseValue() - 2));
    }
}
