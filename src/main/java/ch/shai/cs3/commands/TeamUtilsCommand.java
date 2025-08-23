package ch.shai.cs3.commands;

import ch.shai.cs3.game.Game;
import ch.shai.cs3.game.player.GamePlayer;
import ch.shai.cs3.game.team.GameTeam;
import ch.shai.cs3.utils.parsing.ParsingSlotDyeColor;
import ch.shai.cs3.utils.parsing.ParsingSlotEnum;
import ch.shai.cs3.utils.parsing.ParsingSlotMaterial;
import ch.shai.cs3.utils.parsing.ParsingSlotString;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TeamUtilsCommand<T extends GamePlayer, U extends GameTeam> extends CSCommand{
    private Game<T,U> game;
    public TeamUtilsCommand(Game<T,U> game){
        super(
                new ParsingSlotString("> /teamutils -n Blanc - Cree une team nomee 'Blanc'", "name", "n").setMandatory(true),
                new ParsingSlotEnum<DyeColor>(
                        "> ./teamcreate -n Blanc -c pink - Cree une team nommee Blanc avec avec la couleur rose. Possiblilitees" +
                                String.join(", ",
                                        Arrays.stream(DyeColor.class.getEnumConstants())
                                                .map(Enum::name)
                                                .toArray(String[]::new)), "color", "c").withClass(DyeColor.class)
        );

        this.game = game;
        this.setAdmin(true);
    }

    @Override
    protected void execute(Player player, Location location, World world, String label) {
        GameTeam team = this.game.createTeam();
        String name =(String) this.getSlot(0).getValue(player.getName());
        DyeColor color = (DyeColor) this.getSlot(1).getValue(DyeColor.WHITE);
        team.setName(name);
        team.setColor(color);
        this.game.getGamePlayer(player).sendMessage("La team " + name + " de la couleur " + color.name().toLowerCase() + " a ete cree");
    }


    private enum TeamUtilsCommands {
        CREATE,
        LIST,
        DELETE,
    }
}
