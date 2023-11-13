package ch.shai.cs3.commands;

import ch.shai.cs3.utils.parsing.ParsingSlotBool;
import ch.shai.cs3.utils.parsing.ParsingSlotInt;
import com.mohistmc.api.ItemAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;



public class MyFill extends CSCommand {
    public MyFill(){
        super (
                new ParsingSlotInt("Le parametre -i est le nombre d'item dans l'inventaire qui seront place","i"),
                new ParsingSlotInt("Le parametre -r correspond au rayon du cercle","r"),
                new ParsingSlotInt("Les parametre -d et -p correspondent a la profondeur","d", "p"),
                new ParsingSlotBool("Le parametre -s doit etre a 1 pour sauvgarder la commande dans un item","s").setitemable(false)
        );
    }

    @Override
    protected void execute(Player player, Location location, World world, String label) {
        if ((boolean) this.getSlots()[3].getValue(false))
            world.dropItem(location, ItemAPI.doItem(Material.NAME_TAG, 1, label, this.toLore()));
        int len = (int) this.getSlot(0).getValue(1);
        int xOffset = (int) this.getSlot(1).getValue(0) / 2;
        int yOffset = (int) this.getSlot(2).getValue(location.getBlockY());
        for (int x = -xOffset; x <= xOffset; x++) {
            for (int zOffset = -xOffset; zOffset <= xOffset; zOffset++) {
                for (int y = 0; y < yOffset - 1; y++) {
                    Material type = player.getInventory().getItem(y % len) == null ? Material.AIR : player.getInventory().getItem(len == 0 ? 0 : y % len).getType();
                    world.getBlockAt(location.getBlockX() + x, location.getBlockY() - y, location.getBlockZ() + zOffset).setType(type);
                }
            }
        }
    }




}
