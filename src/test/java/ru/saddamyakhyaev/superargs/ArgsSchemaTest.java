package ru.saddamyakhyaev.superargs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsSchemaTest {

    @Test
    void testSimpleSchema() throws ArgsException {

        var args = new Args(
                ArgsSchema.of('p', ArgsSchema.ArgumentType.INT)
                        .and('k', ArgsSchema.ArgumentType.STRING),
                new String[] {"-p", "10", "-k", "Hello"});

        assertAll(
                () -> assertEquals(10, args.getInt('p')),
                () -> assertEquals("Hello", args.getString('k'))
        );
    }

    @Test
    void testSimpleSchemaWithRequiredError() throws ArgsException {

        var exception = assertThrows(ArgsException.class, () -> new Args(
                ArgsSchema.of('p', ArgsSchema.ArgumentType.INT)
                        .and('k', ArgsSchema.ArgumentType.STRING)
                        .and('l', ArgsSchema.ArgumentType.STRING, ArgsSchema.ArgumentField.REQUIRED),
                new String[] {"-p", "10", "-k", "Hello"}));

        assertAll(
                () -> assertEquals(ArgsException.ErrorCode.REQUIREMENT_ARE_NOT_MET, exception.getErrorCode()),
                () -> assertEquals('l', exception.getErrorArgumentId())
        );
    }


    @Test
    void testSimpleSchemaValuesList() throws ArgsException {

        var args = new Args(
                ArgsSchema.of('p', ArgsSchema.ArgumentType.INT)
                        .and('k', ArgsSchema.ArgumentType.STRING),
                new String[] {"-p", "10", "-k", "Hello"});

        assertAll(
                () -> assertEquals(10, args.getInt('p')),
                () -> assertEquals("Hello", args.getString('k'))
        );
    }
}
