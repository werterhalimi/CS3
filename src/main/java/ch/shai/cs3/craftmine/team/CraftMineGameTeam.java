package ch.shai.cs3.craftmine.team;

import ch.shai.cs3.craftmine.player.CraftMineGamePlayer;
import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.team.GameTeam;
import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class CraftMineGameTeam extends GameTeam {
    private static final int TEAM_SIZE = 1;

    public CraftMineGameTeam(String name, DyeColor color, Location spawn) {
        super(new ArrayList<>(), name, color, spawn, CraftMineGameTeam.TEAM_SIZE);
    }


}
