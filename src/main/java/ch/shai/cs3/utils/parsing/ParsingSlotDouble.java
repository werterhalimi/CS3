package ch.shai.cs3.utils.parsing;

import java.text.ParseException;

public class ParsingSlotDouble extends ParsingSlot<Double>{
    public ParsingSlotDouble(String description, String... aliases) {
        super(description, aliases);
    }

    @Override
    protected boolean checkValue(String token, String value) throws ParseException {
        try {
            System.out.println("DOUBLE " + token + " " + value);
            this.value = Double.parseDouble(value);
            return true;
        }catch (Exception e){
            try {
                System.out.println("DOUBLE TRY INT " + token + " " + value);
                this.value = (double) Integer.parseInt(value);
                return true;
            }catch (Exception ex){
                throw new ParseException(value + " devrait etre un nombre vers " + token + " " + value, 1);
            }
        }
    }
}
