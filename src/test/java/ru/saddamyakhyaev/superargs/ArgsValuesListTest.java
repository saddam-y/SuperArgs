package ru.saddamyakhyaev.superargs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.saddamyakhyaev.superargs.ArgsException.ErrorCode.NOT_AVAILABLE_VALUE_LIST;

public class ArgsValuesListTest {


    @Test
    void testNotAvailableValueListForIntPresent() throws Exception {

        var exception = assertThrows(ArgsException.class, () -> new Args("x#", new String[]{"-x", "12", "-x", "12"}));
        ArgsTest.testArgsException(exception, 'x', "12", NOT_AVAILABLE_VALUE_LIST);
    }

    @Test
    void testAvailableValueListForIntPresent() throws Exception {
        var args = new Args("x#[]", new String[]{"-x", "12", "-x", "24", "-x", "324"});
        var valueList = args.getIntList('x');

        assertAll(
                () -> assertEquals(3, valueList.size()),
                () -> assertEquals(12, valueList.get(0)),
                () -> assertEquals(24, valueList.get(1)),
                () -> assertEquals(324, valueList.get(2))
        );
    }

    @Test
    void testNotAvailableValueListForDoublePresent() throws Exception {

        var exception = assertThrows(ArgsException.class, () -> new Args("p##", new String[]{"-p", "32.45", "-p", "75.2"}));
        ArgsTest.testArgsException(exception, 'p', "75.2", NOT_AVAILABLE_VALUE_LIST);
    }

    @Test
    void testAvailableValueListForDoublePresent() throws Exception {
        var args = new Args("p##[]", new String[]{"-p", "32.45", "-p", "75.2"});
        var valueList = args.getDoubleList('p');

        assertAll(
                () -> assertEquals(2, valueList.size()),
                () -> assertEquals(32.45, valueList.get(0)),
                () -> assertEquals(75.2, valueList.get(1))
        );
    }

    @Test
    void testNotAvailableValueListForStringPresent() throws Exception {

        var exception = assertThrows(ArgsException.class, () -> new Args("s*", new String[]{"-s", "Cu?", "-s", "Prime"}));
        ArgsTest.testArgsException(exception, 's', "Prime", NOT_AVAILABLE_VALUE_LIST);
    }

    @Test
    void testAvailableValueListForStringPresent() throws Exception {
        var args = new Args("s*[]", new String[]{"-s", "How are you?", "-s", "Hello, World"});
        var valueList = args.getStringList('s');

        assertAll(
                () -> assertEquals(2, valueList.size()),
                () -> assertEquals("How are you?", valueList.get(0)),
                () -> assertEquals("Hello, World", valueList.get(1))
        );
    }

}
