package ru.saddamyakhyaev.superargs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsSchema {

    private Map<Character, ArgumentType> argumentTypes;
    private Map<Character, ArgumentField[]> argumentFields;

    public ArgsSchema() {
        this.argumentTypes = new HashMap<>();
        this.argumentFields = new HashMap<>();
    }

    public static ArgsSchema of(char element, ArgumentType type, ArgumentField... fields) {
            var argsSchema = new ArgsSchema();
            return argsSchema.and(element, type, fields);
    }

    public ArgsSchema and(char element, ArgumentType type, ArgumentField... fields) {
        argumentTypes.put(element, type);
        argumentFields.put(element, fields);
        return this;
    }

    public String getSchemaString() {
        StringBuilder result = new StringBuilder();
        for(char element: argumentTypes.keySet()) {
            result.append(element).append(argumentTypes.get(element).getKey());
            for(var field: argumentFields.get(element)) {
                result.append(field.getKey());
            }
            result.append(",");
        }
        return result.toString();
    }

    public enum ArgumentType {
        INT("#"), DOUBLE("##"), BOOLEAN(""), STRING("*");

        private final String key;

        ArgumentType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public enum ArgumentField {
        VALUES_LIST("[]"), REQUIRED("!");

        private final String key;

        ArgumentField(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}
