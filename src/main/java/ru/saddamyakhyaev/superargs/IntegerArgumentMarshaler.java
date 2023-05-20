package ru.saddamyakhyaev.superargs;

import java.util.*;

public class IntegerArgumentMarshaler extends ArgumentMarshaler<Integer> {


    public IntegerArgumentMarshaler(boolean availableValuesList, boolean required) {
        super(availableValuesList, required);
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




}
