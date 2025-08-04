package ch.shai.cs3;


import ch.shai.cs3.craftmine.CraftMineGame;
import ch.shai.cs3.events.PlayerInteract;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {
//
//    public void registerCommand(CSCommand csCommand, String name){
//        PluginCommand command = getServer().getPluginCommand(name);
//        command.setExecutor(csCommand);
////        command.setTabCompleter(new MyFillCompleter(csCommand));
//    }
//
//    public void registerCommand(CSCommand csCommand, String name, TabCompleter tabCompleter){
//        PluginCommand command = getServer().getPluginCommand(name);
//        command.setExecutor(csCommand);
//        command.setTabCompleter(tabCompleter);
//    }


    @Override
    public void onEnable() {
        new CraftMineGame(this).enable();
//        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
//        registerCommand(new MyFill(), "myfill");
//        registerCommand(new GenTree(), "gentree");
//        registerCommand(new Gif(this), "gif");
//        registerCommand(new GenWorld(), "genworld");
//        registerCommand(new Teleport(), "tpx", new TpxCompleter());
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
