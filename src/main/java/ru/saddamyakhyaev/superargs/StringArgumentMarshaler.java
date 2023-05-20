package ru.saddamyakhyaev.superargs;

import java.util.*;
import java.util.stream.Stream;

public class StringArgumentMarshaler implements ArgumentMarshaler {
    private final List<String> values;
    private final boolean availableValuesList;

    public StringArgumentMarshaler(boolean availableValuesList) {
        this.availableValuesList = availableValuesList;
        this.values = new ArrayList<>();
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

    @Override
    public boolean isAvailableValuesList() {
        return availableValuesList;
    }

    public static String getValue(ArgumentMarshaler am) {
        if (am instanceof StringArgumentMarshaler stringArgumentMarshaler)
            return stringArgumentMarshaler.values.get(0);
        else
            return "";
    }

    public static List<String> getValues(ArgumentMarshaler am) {
        if (am instanceof StringArgumentMarshaler stringArgumentMarshaler) {
            return stringArgumentMarshaler.values;
        }else
            return Collections.emptyList();
    }
}
