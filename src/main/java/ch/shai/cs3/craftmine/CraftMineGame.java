package ch.shai.cs3.craftmine;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.state.CraftMineGameState;
import ch.shai.cs3.craftmine.state.WorldGenerationState;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.state.sample.hub.HubGameState;
import ch.shai.cs3.game.state.sample.hub.HubGameStateOption;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

public class CraftMineGame extends Game<CraftMineGamePlayer, CraftMineGameTeam> {
    private ConcurrentHashMap<Material, Long> blockCounts;

    public CraftMineGame(JavaPlugin plugin) {
        super(plugin, "CraftMine");
        this.addState(new WorldGenerationState());
        this.addState(new HubGameState<CraftMineGamePlayer, CraftMineGameTeam>(this.getWorld().getSpawnLocation().add(0,40,0),new HubGameStateOption()));
        this.addState(new CraftMineGameState());
        this.teams.add(new CraftMineGameTeam("Les verts !", DyeColor.GREEN,this.getWorld().getSpawnLocation() ));
        this.teams.add(new CraftMineGameTeam("Les bleus !", DyeColor.BLUE,this.getWorld().getSpawnLocation() ));
        this.teams.add(new CraftMineGameTeam("Les jaunes !", DyeColor.YELLOW, this.getWorld().getSpawnLocation()));
    }

    public void setBlockCounts(ConcurrentHashMap<Material, Long> blockCounts) {
        this.blockCounts = blockCounts;
    }
    public ConcurrentHashMap<Material, Long> getBlockCounts() {
        return blockCounts;
    }

    @Override
    public CraftMineGameTeam createTeam() {
        CraftMineGameTeam team = new CraftMineGameTeam(null, null, this.getWorld().getSpawnLocation());
        this.teams.add(team);
        return team;
    }


    public CraftMineGamePlayer registerPlayer(Player player) {
        CraftMineGamePlayer gamePlayer = new CraftMineGamePlayer(player, this);
        this.players.add(gamePlayer);
        return gamePlayer;
    }


    public void unRegisterPlayer(CraftMineGamePlayer player) {
        this.players.remove(player);
        if (player.getTeam() != null) {
            player.getTeam().removePlayer(player);
        }
    }
}
