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

        line.processFrame(new int[]{10,0});
        assertEquals(10, line.getFrames().get(0).getScore());

        line.processFrame(new int[]{10,0});
        assertEquals(10, line.getFrames().get(1).getScore());
        assertEquals(20, line.getFrames().get(0).getScore());

        line.processFrame(new int[]{10,0});
        assertEquals(10, line.getFrames().get(2).getScore());
        assertEquals(20, line.getFrames().get(1).getScore());
        assertEquals(30, line.getFrames().get(0).getScore());

        line.processFrame(new int[]{10,0});
        assertEquals(10, line.getFrames().get(3).getScore());
        assertEquals(20, line.getFrames().get(2).getScore());
        assertEquals(30, line.getFrames().get(1).getScore());
        assertEquals(30, line.getFrames().get(0).getScore());
    }

    @Test
    public void scoreCalcTest() {
        Line line = new Line();

        line.processFrame(new int[]{6,2});
        assertEquals(8, line.getFrames().get(0).getScore());

        line.processFrame(new int[]{5,5});
        assertEquals(10, line.getFrames().get(1).getScore());
        assertEquals(8, line.getFrames().get(0).getScore());

        line.processFrame(new int[]{7,0});
        assertEquals(7, line.getFrames().get(2).getScore());
        assertEquals(17, line.getFrames().get(1).getScore());
        assertEquals(8, line.getFrames().get(0).getScore());
    }

    @Test
    public void processWholeLineTest() {
        Line line1 = new Line();
        line1.processWholeLine("x x x x x x x x x x x x");
        assertEquals(300, line1.getLineScore());

        Line line2 = new Line();
        line2.processWholeLine("x 8- 4/ 3/ 5/ 31 53 x 45 16");
        assertEquals(114, line2.getLineScore());

        Line line3 = new Line();
        line3.processWholeLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5");
        assertEquals(150, line3.getLineScore());

        Line line4 = new Line();
        line4.processWholeLine("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-");
        assertEquals(90, line4.getLineScore());

        Line line5 = new Line();
        line5.processWholeLine("44 3/ 35 15 81 71 x -4 2/ 7/ 6");
        assertEquals(103, line5.getLineScore());

        Line line6 = new Line();
        line6.processWholeLine("25 72 9- 5- 7/ 24 7/ -- -- 37 x");
        assertEquals(78, line6.getLineScore());
    }
}