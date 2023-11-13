package ch.shai.cs3.utils.parsing;

import java.text.ParseException;

public class ParsingSlotBool extends ParsingSlot<Boolean>{

    public ParsingSlotBool(String description ,String... aliases) {
        super(description, aliases);
    }



    @Override
    protected boolean checkValue(String token, String value) throws ParseException {
        try {
            System.out.println("BOOL " + token + " " + value);
            this.value = value.contentEquals("1") || Boolean.parseBoolean(value);
            return true;
        }catch (Exception e){
            throw new ParseException(value + " devrait etre [true, True, 1] ou [false, False, 0] vers " + token + " " + value, 1);
        }
    }

}
