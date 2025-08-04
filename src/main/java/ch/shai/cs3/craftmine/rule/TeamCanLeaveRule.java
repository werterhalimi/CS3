package ch.shai.cs3.craftmine.rule;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.craftmine.team.CraftMineGameTeam;
import ch.shai.cs3.game.state.rule.GameStateRule;
import ch.shai.cs3.utils.structure.SquareStructure;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPortalEnterEvent;

import java.util.List;

import static ch.shai.cs3.utils.itemstack.WhoolUtils.getColoredWool;
import static ch.shai.cs3.utils.structure.WorldBorderStructureUtils.getEquidistantPointsInBorder;

public class TeamCanLeaveRule extends GameStateRule<CraftMineGamePlayer, CraftMineGameTeam> {

    private CraftMineGameTeam getTeamPortal(Location location){
        for (CraftMineGameTeam team : this.getState().getGame().getTeams()) {
            if (team.getExitPortalCenter().distance(location) <= 3){
                return team;
            }
        }
        return null;
    }

    @EventHandler
    public void onEnterEndPortal(EntityPortalEnterEvent event){
        Bukkit.broadcastMessage(event.getPortalType().name());
        CraftMineGameTeam portalTeam = this.getTeamPortal(event.getLocation());


        if (portalTeam != null) {
            event.setCancelled(true);
            if (event.getEntity() instanceof Player){
                CraftMineGamePlayer player = this.getState().getGame().getGamePlayer((Player) event.getEntity());
                if (!player.isPlaying()){
                    event.setCancelled(true);
                    return ;
                }
                if (portalTeam != player.getTeam()){
                    event.setCancelled(true);
                    player.getBukkitPlayer().sendMessage("Felicitation mais ce portail n'est pas de la bonne couleur.");
                    return ;
                }
                player.setPlaying(false);
                player.getBukkitPlayer().setGameMode(GameMode.SPECTATOR);
            }
        }
    }

    @Override
    public void onLoad() {
        List<Location> locations = getEquidistantPointsInBorder(this.getState().getGame().getWorld(), this.getState().getGame().getTeams().size());
        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            CraftMineGameTeam team = this.getState().getGame().getTeams().get(i);

            team.setSpawnPoint(location.clone().add(0, 2, 1));

            SquareStructure.createRoom(location.clone().add(5, 1, 5), location.clone().add(-5,-4,-5), getColoredWool(team.getColor()).getType());
            SquareStructure.createFilledCube(location.clone().add(-1,-4,-1),location.clone().add(1,-4,1),Material.END_PORTAL);

            team.setExitPortalCenter(location.add(0,-4,0));
        }
    }
}
