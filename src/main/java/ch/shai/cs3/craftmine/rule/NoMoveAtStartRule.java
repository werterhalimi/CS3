package ch.shai.cs3.craftmine.rule;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.utils.logger.LoggerUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoMoveAtStartRule extends GameStateRule<CraftMineGamePlayer, CraftMineGameTeam> {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        event.setCancelled(true);
    }


    @Override
    public void onLoad() {
        super.onLoad();
        LoggerUtils.announce( "Le jeu va commencer dans 5 secondes");
        Bukkit.getScheduler().runTaskLater(this.getState().getGame().getPlugin(), new NoMoveAtStartRuleRunnable(this),5 * 20);
    }

    private static class NoMoveAtStartRuleRunnable implements Runnable {
        private GameStateRule rule;

        public NoMoveAtStartRuleRunnable(NoMoveAtStartRule noMoveAtStartRule){
            this.rule = noMoveAtStartRule;
        }

        @Override
        public void run() {
            this.rule.disable();
            LoggerUtils.announce("Le jeu commence");
        }
    }
}
