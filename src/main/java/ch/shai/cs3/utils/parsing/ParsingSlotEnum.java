package ch.shai.cs3.utils.parsing;

import org.bukkit.Material;

import java.text.ParseException;

public class ParsingSlotEnum<T extends Enum<T>> extends ParsingSlot<T>{
    private Class<T> enumClass;

    public ParsingSlotEnum(String description, String... aliases) {
        super(description, aliases);
    }

    public ParsingSlotEnum<T> withClass(Class<T> enumClass) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException(enumClass.getName() + " n'est pas une enum");
        }
        this.enumClass = enumClass;
        return this;
    }

    @Override
    protected boolean checkValue(String token, String value) throws ParseException {
        if (enumClass == null) {
            throw new IllegalStateException("Enum class must be set before parsing. Call withClass(...) first.");
        }
        try {
            this.value = Enum.valueOf(enumClass, value.toUpperCase());
            return true;
        }catch (Exception e){
            throw new ParseException(value + " devrait etre une enum vers " + token + " " + value, 1);
        }
    }
}
