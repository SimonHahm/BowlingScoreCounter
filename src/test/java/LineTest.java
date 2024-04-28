import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void scoreCalcTestOnlyStrikes() {
        Line line = new Line();
        line.setFrames(new ArrayList<>(List.of()));

        line.frameScoreCalc(new int[]{10,0});
        assertEquals(10, line.getFrames().get(0).getScore());

        line.frameScoreCalc(new int[]{10,0});
        assertEquals(10, line.getFrames().get(1).getScore());
        assertEquals(20, line.getFrames().get(0).getScore());

        line.frameScoreCalc(new int[]{10,0});
        assertEquals(10, line.getFrames().get(2).getScore());
        assertEquals(20, line.getFrames().get(1).getScore());
        assertEquals(30, line.getFrames().get(0).getScore());

        line.frameScoreCalc(new int[]{10,0});
        assertEquals(10, line.getFrames().get(3).getScore());
        assertEquals(20, line.getFrames().get(2).getScore());
        assertEquals(30, line.getFrames().get(1).getScore());
        assertEquals(30, line.getFrames().get(0).getScore());
    }

    @Test
    public void scoreCalcTest() {
        Line line = new Line();
        line.setFrames(new ArrayList<>(List.of()));

        line.frameScoreCalc(new int[]{6,2});
        assertEquals(8, line.getFrames().get(0).getScore());

        line.frameScoreCalc(new int[]{5,5});
        assertEquals(10, line.getFrames().get(1).getScore());
        assertEquals(8, line.getFrames().get(0).getScore());

        line.frameScoreCalc(new int[]{7,0});
        assertEquals(7, line.getFrames().get(2).getScore());
        assertEquals(17, line.getFrames().get(1).getScore());
        assertEquals(8, line.getFrames().get(0).getScore());
    }
}