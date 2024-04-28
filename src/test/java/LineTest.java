import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    @Test
    public void inputParserTest() {
        int[] rolls = {10,0};           //strike
        assertArrayEquals(rolls, Line.parseInput("X"));

        int[] rolls2 = {3,7};           //spare
        assertArrayEquals(rolls2, Line.parseInput("3/"));

        int[] rolls5 = {0,10};          //spare
        assertArrayEquals(rolls5, Line.parseInput("-/"));

        int[] rolls4 = {0,0};           //Gutter
        assertArrayEquals(rolls4, Line.parseInput("--"));

        int[] rolls3 = {5,0};
        assertArrayEquals(rolls3, Line.parseInput("5-"));

        int[] rolls6 = {8,1};
        assertArrayEquals(rolls6, Line.parseInput("81"));
    }
}