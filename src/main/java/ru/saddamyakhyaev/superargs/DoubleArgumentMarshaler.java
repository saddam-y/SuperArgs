package ru.saddamyakhyaev.superargs;

import java.util.*;
import java.util.stream.Stream;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
    private final List<Double> values;
    private final boolean availableValuesList;

    public DoubleArgumentMarshaler(boolean availableValuesList) {
        this.availableValuesList = availableValuesList;
        this.values = new ArrayList<>();
    }

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            if(!isAvailableValuesList() && values.size() > 0) {
                throw new ArgsException(ArgsException.ErrorCode.NOT_AVAILABLE_VALUE_LIST, parameter);
            }else {
                values.add(Double.valueOf(parameter));
            }
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_DOUBLE, parameter);
        } catch (NumberFormatException e) {
            throw new ArgsException(ArgsException.ErrorCode.INVALID_DOUBLE, parameter);
        }
    }

    @Override
    public boolean isAvailableValuesList() {
        return availableValuesList;
    }

    public static Double getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof DoubleArgumentMarshaler doubleArgumentMarshaler)
            return doubleArgumentMarshaler.values.get(0);
        else
            return 0.0;
    }

    public static List<Double> getValues(ArgumentMarshaler am) {
        if (am instanceof DoubleArgumentMarshaler doubleArgumentMarshaler) {
            return doubleArgumentMarshaler.values;
        }else
            return Collections.emptyList();
    }
}
