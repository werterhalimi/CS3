package ch.shai.cs3.utils.parsing;

import java.text.ParseException;

public class ParsingSlotString extends ParsingSlot<String>{
    public ParsingSlotString(String description, String... aliases) {
        super(description, aliases);
    }

    @Override
    protected boolean checkValue(String token, String value) throws ParseException {
        try {
            System.out.println("STRING " + token + " " + value);
            this.value = (value);
            return true;
        }catch (Exception e){
            throw new ParseException(value + " devrait etre un nombre vers " + token + " " + value, 1);
        }
    }
}
