package ch.shai.cs3.utils.parsing;

import java.text.ParseException;
import java.util.Arrays;

public abstract class ParsingSlot<T> {

    private boolean itemable;
    private boolean mandatory;
    private String description;
    private String aliases[];
    protected T value;

    public ParsingSlot(String description, String... aliases) {
        this.aliases = aliases;
        this.description = description;
        this.itemable = true;
        this.mandatory = false;
        this.emptyValue();
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean hasValue() {
        return this.value != null;
    }
    public void emptyValue() {
        this.value = null;
    }

    public T getValue(T defaultValue) {
        if (this.value != null) return this.value;
        return defaultValue;
    }

    public T getValue() {
        return this.value;
    }

    public ParsingSlot<T> setMandatory(boolean mandatory){
        this.mandatory = mandatory;
        return  this;
    }


    public ParsingSlot<T> setitemable(boolean itemable){
        this.itemable = itemable;
        return  this;
    }

    public boolean isToken(String token){
        for (String alias : this.aliases) {
            if (token.contentEquals("-" + alias)){
                return true;
            }
        }
        return false;
    }

    protected abstract boolean checkValue(String token, String value) throws ParseException;

    public void checkToken(String token, String next) throws ParseException {
        if (this.isToken(token)) this.checkValue(token, next);
    }

    public String getDescription() {
        return description;
    }

    public String getKey() {
        return this.aliases[0];
    }

    public boolean isItemable(){
        return this.itemable;
    }

    @Override
    public String toString() {
        return "ParsingSlot{" +
                "aliases=" + Arrays.toString(aliases) +
                ", value='" + value + '\'' +
                '}';
    }
}
