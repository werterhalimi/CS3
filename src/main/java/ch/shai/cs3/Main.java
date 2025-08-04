package ch.shai.cs3;


import ch.shai.cs3.craftmine.CraftMineGame;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new CraftMineGame(this).enable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
