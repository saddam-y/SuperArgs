package ru.saddamyakhyaev.superargs;

import java.util.Iterator;

public class BooleanArgumentMarshaler extends ArgumentMarshaler<Boolean> {

    public BooleanArgumentMarshaler(boolean required) {
       super(false, required);
       values.add(false);
    }

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        values.add(0, true);
    }

}
