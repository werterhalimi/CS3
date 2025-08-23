package ch.shai.cs3.utils.parsing;

import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.text.ParseException;

public class ParsingSlotDyeColor extends ParsingSlot<DyeColor>{
    public ParsingSlotDyeColor(String description, String... aliases) {
        super(description, aliases);
    }

    @Override
    protected boolean checkValue(String token, String value) throws ParseException {
        try {
            System.out.println("Material " + token + " " + value);
            this.value = DyeColor.valueOf(value.toUpperCase());
            return true;
        }catch (Exception e){
            throw new ParseException(value + " devrait etre une couleur vers " + token + " " + value, 1);
        }
    }
}
