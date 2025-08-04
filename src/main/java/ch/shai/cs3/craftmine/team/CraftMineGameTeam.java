package ch.shai.cs3.craftmine.team;

import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.ArrayList;

public class CraftMineGameTeam extends GameTeam {
    private static final int TEAM_SIZE = 1;

    private Location exitPortalCenter;

    public CraftMineGameTeam(String name, DyeColor color, Location spawn) {
        super(new ArrayList<>(), name, color, spawn, CraftMineGameTeam.TEAM_SIZE);
    }

    public void setExitPortalCenter(Location newVal){
        this.exitPortalCenter = newVal;
    }

    public Location getExitPortalCenter(){
        return this.exitPortalCenter;
    }
}
