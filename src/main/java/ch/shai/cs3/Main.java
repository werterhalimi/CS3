package ch.shai.cs3;

import ch.shai.cs3.commands.*;
import ch.shai.cs3.commands.completers.MyFillCompleter;
import ch.shai.cs3.commands.completers.TpxCompleter;
import ch.shai.cs3.events.PlayerInteract;
import ch.shai.cs3.game.Game;
import ch.shai.cs3.turbine.InGameState;
import ch.shai.cs3.turbine.TurbineGame;
import ch.shai.cs3.game.state.GameState;
import ch.shai.cs3.turbine.WaitingForPlayerState;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public final class Main extends JavaPlugin {

    public void registerCommand(CSCommand csCommand, String name){
        PluginCommand command = getServer().getPluginCommand(name);
        command.setExecutor(csCommand);
        command.setTabCompleter(new MyFillCompleter(csCommand));
    }

    public void registerCommand(CSCommand csCommand, String name, TabCompleter tabCompleter){
        PluginCommand command = getServer().getPluginCommand(name);
        command.setExecutor(csCommand);
        command.setTabCompleter(tabCompleter);
    }


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        registerCommand(new MyFill(), "myfill");
        registerCommand(new GenTree(), "gentree");
        registerCommand(new Gif(this), "gif");
        registerCommand(new GenWorld(), "genworld");
        registerCommand(new Teleport(), "tpx", new TpxCompleter());
//
//
//        GameState waitingForPlayerState = new WaitingForPlayerState();
//        Game game = new TurbineGame(this, waitingForPlayerState);
//        game.addState(new InGameState());
//        game.loadCurrentStep();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
