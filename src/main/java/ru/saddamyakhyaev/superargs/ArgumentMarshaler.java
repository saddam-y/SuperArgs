package ru.saddamyakhyaev.superargs;

import java.util.Iterator;

public interface ArgumentMarshaler {
    void set(Iterator<String> currentArgument) throws ArgsException;
    default boolean isAvailableValuesList() {
        return false;
    }
}
