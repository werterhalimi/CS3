package ch.shai.cs3.utils.parsing;

import org.bukkit.Material;

import java.text.ParseException;

public class ParsingSlotMaterial extends ParsingSlot<Material>{
    public ParsingSlotMaterial(String description, String... aliases) {
        super(description, aliases);
    }

    @Override
    protected boolean checkValue(String token, String value) throws ParseException {
        try {
            System.out.println("Material " + token + " " + value);
            this.value = Material.valueOf(value.toUpperCase());
            return true;
        }catch (Exception e){
            throw new ParseException(value + " devrait etre un Material vers " + token + " " + value, 1);
        }
    }
}
