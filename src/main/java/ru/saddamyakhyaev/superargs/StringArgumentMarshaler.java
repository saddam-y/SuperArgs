package ru.saddamyakhyaev.superargs;

import java.util.*;

public class StringArgumentMarshaler extends ArgumentMarshaler<String> {


    public StringArgumentMarshaler(boolean availableValuesList, boolean required) {
        super(availableValuesList, required);
    }

    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            var parameter = currentArgument.next();
            if(!isAvailableValuesList() && values.size() > 0) {
                throw new ArgsException(ArgsException.ErrorCode.NOT_AVAILABLE_VALUE_LIST, parameter);
            }else {
                values.add(parameter);
            }
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING);
        }
    }

}
