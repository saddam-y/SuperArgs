package ru.saddamyakhyaev.superargs;

import java.util.*;
import java.util.stream.Stream;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
    private List<Integer> values = new ArrayList<>();
    private boolean availableValuesList = true;


    public IntegerArgumentMarshaler(boolean availableValuesList) {
        this.availableValuesList = availableValuesList;
    }

    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            if(!isAvailableValuesList() && (parameter.split("\\s").length > 1 || values.size() > 0)) {
                throw new ArgsException(ArgsException.ErrorCode.NOT_AVAILABLE_VALUE_LIST, parameter);
            }else {
                values.addAll(Stream.of(parameter.split("\\s")).map(Integer::valueOf).toList());
            }
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_INTEGER, parameter);
        } catch (NumberFormatException e) {
            throw new ArgsException(ArgsException.ErrorCode.INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaler am) {
        if (am instanceof IntegerArgumentMarshaler)
            return ((IntegerArgumentMarshaler) am).values.get(0);
        else
            return 0;
    }

    public static List<Integer> getValues(ArgumentMarshaler am) {
        if (am instanceof IntegerArgumentMarshaler intArrayAm) {
            return intArrayAm.values;
        }else
            return Collections.emptyList();
    }

    public boolean isAvailableValuesList() {
        return availableValuesList;
    }



}
