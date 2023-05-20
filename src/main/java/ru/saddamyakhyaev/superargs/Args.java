package ru.saddamyakhyaev.superargs;

import java.util.*;

public class Args {
    private final Map<Character, ArgumentMarshaler> marshalers;
    private Set<Character> argsFound;
    private ListIterator<String> currentArgument;

    public Args(String schema, String[] args) throws ArgsException {
        marshalers = new HashMap<Character, ArgumentMarshaler>();
        argsFound = new HashSet<Character>();

        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(args));

        requirementsAreMet();
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element : schema.split(","))
            if (element.length() > 0)
                parseSchemaElement(element.trim());
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        boolean isValueList = elementTail.contains("[]");
        boolean isRequired = elementTail.contains("!");
        if(isValueList) {
            elementTail = elementTail.replace("[]", "");
        }
        if(isRequired) {
            elementTail = elementTail.replace("!", "");
        }
        validateSchemaElementId(elementId);
        if (elementTail.length() == 0)
            marshalers.put(elementId, new BooleanArgumentMarshaler(isRequired));
        else if (elementTail.equals("*"))
            marshalers.put(elementId, new StringArgumentMarshaler(isValueList, isRequired));
        else if (elementTail.equals("#"))
            marshalers.put(elementId, new IntegerArgumentMarshaler(isValueList, isRequired));
        else if (elementTail.equals("##"))
            marshalers.put(elementId, new DoubleArgumentMarshaler(isValueList, isRequired));
        else
            throw new ArgsException(ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId))
            throw new ArgsException(ArgsException.ErrorCode.INVALID_ARGUMENT_NAME, elementId, null);
    }

    private void parseArgumentStrings(List<String> argsList) throws ArgsException
    {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext();)
        {
            String argString = currentArgument.next();
            if (argString.startsWith("-")) {
                parseArgumentCharacters(argString.substring(1));
            } else {
                currentArgument.previous();
                break;
            }
        }
    }
    private void parseArgumentCharacters(String argChars) throws ArgsException {
        for (int i = 0; i < argChars.length(); i++)
            parseArgumentCharacter(argChars.charAt(i));
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        var m = marshalers.get(argChar);
        if (m == null) {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, argChar, null);
        } else {
            argsFound.add(argChar);
            try {
                m.set(currentArgument);
            } catch (ArgsException e) {
                e.setErrorArgumentId(argChar);
                throw e;
            }
        }
    }

    public void requirementsAreMet() throws ArgsException {
        for(var key: marshalers.keySet()) {
            var m = marshalers.get(key);
            if(m.isRequired() && m.getValues().size() == 0) {
                throw new ArgsException(ArgsException.ErrorCode.REQUIREMENT_ARE_NOT_MET, key, null);
            }
        }
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

    public int nextArgument() {
        return currentArgument.nextIndex();
    }

    public boolean getBoolean(char arg) throws ArgsException {
        if(marshalers.get(arg) instanceof BooleanArgumentMarshaler booleanArgumentMarshaler){
            return booleanArgumentMarshaler.getValue();
        } else {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

    public String getString(char arg) throws ArgsException {
        if(marshalers.get(arg) instanceof StringArgumentMarshaler stringArgumentMarshaler){
            return stringArgumentMarshaler.getValue();
        } else {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }
    public List<String> getStringList(char arg) throws ArgsException {
        if(marshalers.get(arg) instanceof StringArgumentMarshaler stringArgumentMarshaler){
            return stringArgumentMarshaler.getValues();
        } else {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

    public int getInt(char arg) throws ArgsException {
        if(marshalers.get(arg) instanceof IntegerArgumentMarshaler integerArgumentMarshaler){
            return integerArgumentMarshaler.getValue();
        } else {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

    public List<Integer> getIntList(char arg) throws ArgsException {
        if(marshalers.get(arg) instanceof IntegerArgumentMarshaler integerArgumentMarshaler){
            return integerArgumentMarshaler.getValues();
        } else {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

    public double getDouble(char arg) throws ArgsException {
        if(marshalers.get(arg) instanceof DoubleArgumentMarshaler doubleArgumentMarshaler){
            return doubleArgumentMarshaler.getValue();
        } else {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

    public List<Double> getDoubleList(char arg) throws ArgsException {
        if(marshalers.get(arg) instanceof DoubleArgumentMarshaler doubleArgumentMarshaler){
            return doubleArgumentMarshaler.getValues();
        } else {
            throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

}

