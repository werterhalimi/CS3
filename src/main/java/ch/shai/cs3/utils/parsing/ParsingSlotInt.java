package ch.shai.cs3.utils.parsing;

import java.text.ParseException;

public class ParsingSlotInt extends ParsingSlot<Integer>{


    public ParsingSlotInt(String description, String... aliases) {
        super(description, aliases);
    }

    @Override
    protected boolean checkValue(String token, String value) throws ParseException {
        try {
            System.out.println("INT " + token + " " + value);
            this.value = Integer.parseInt(value);
            return true;
        }catch (Exception e){
            throw new ParseException(value + " devrait etre un chiffre vers " + token + " " + value, 1);
        }
    }


}
