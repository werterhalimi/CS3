package ch.shai.cs3.turbine;

import ch.shai.cs3.game.state.rule.GameStateRule;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class DestroyBlockRule extends GameStateRule implements Listener {

    private BukkitRunnable runnable;

    @Override
    public void onLoad() {
        this.runnable = new DestroyRunnable((TurbineGame) this.getState().getGame());
        System.out.println("InGameLoad");
        this.runnable.runTaskTimer(this.getState().getGame().getPlugin(),0, 5);
    }

    @Override
    public void unLoad() {
        this.runnable.cancel();
    }
}
