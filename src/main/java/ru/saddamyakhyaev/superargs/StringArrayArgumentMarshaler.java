package ru.saddamyakhyaev.superargs;

import java.util.*;
import java.util.stream.Collectors;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    private List<String> stringArrayValue = new ArrayList<>();

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            Collections.addAll(stringArrayValue, parameter.split("\\s"));
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING);
        }
    }

    public static String[] getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArrayArgumentMarshaler) {
            var stringArrayAm = (StringArrayArgumentMarshaler) am;
            return stringArrayAm.stringArrayValue.stream().toArray(String[]::new);
        }else
            return new String[] {};
    }
}
