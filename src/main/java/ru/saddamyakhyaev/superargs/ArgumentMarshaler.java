package ru.saddamyakhyaev.superargs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ArgumentMarshaler <T> {
    protected final List<T> values;
    protected final boolean availableValuesList;
    protected final boolean required;

    public ArgumentMarshaler(boolean availableValuesList, boolean required) {
        this.availableValuesList = availableValuesList;
        this.required = required;
        this.values = new ArrayList<>();
    }
    protected boolean isAvailableValuesList() {
        return availableValuesList;
    }

    protected boolean isRequired(){
        return required;
    }

    protected T getValue() {
       return values.get(0);
    }

    protected List<T> getValues() {
       return values;
    }

    abstract void set(Iterator<String> currentArgument) throws ArgsException;
}
