package ru.saddamyakhyaev.superargs;

import java.util.*;
import java.util.stream.Stream;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
    private final List<Integer> values;
    private final boolean availableValuesList;


    public IntegerArgumentMarshaler(boolean availableValuesList) {
        this.availableValuesList = availableValuesList;
        this.values = new ArrayList<>();
    }

    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            if(!isAvailableValuesList() && values.size() > 0) {
                throw new ArgsException(ArgsException.ErrorCode.NOT_AVAILABLE_VALUE_LIST, parameter);
            }else {
                values.add(Integer.valueOf(parameter));
            }
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_INTEGER, parameter);
        } catch (NumberFormatException e) {
            throw new ArgsException(ArgsException.ErrorCode.INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaler am) {
        if (am instanceof IntegerArgumentMarshaler integerArgumentMarshaler)
            return integerArgumentMarshaler.values.get(0);
        else
            return 0;
    }

    public static List<Integer> getValues(ArgumentMarshaler am) {
        if (am instanceof IntegerArgumentMarshaler integerArgumentMarshaler) {
            return integerArgumentMarshaler.values;
        }else
            return Collections.emptyList();
    }

    public boolean isAvailableValuesList() {
        return availableValuesList;
    }



}
