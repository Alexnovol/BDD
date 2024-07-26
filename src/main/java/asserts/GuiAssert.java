package asserts;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuiAssert {

    public static <T> void shouldBeEquals(T expected, T actual) {
        assertEquals(expected, actual);
    }
}
