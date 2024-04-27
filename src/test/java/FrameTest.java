import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {
    @Test
    public void testConstructor() {
        Frame testFrame = new Frame(4,2);
        assertEquals(6,testFrame.getScore());
    }

    @Test
    public void testStrike() {
        Frame testFrame = new Frame(10,0);
        assertEquals(10,testFrame.getScore());
        assertTrue(testFrame.isStrike());
        assertFalse(testFrame.isSpare());
    }

    @Test
    public void testSpare() {
        Frame testFrame = new Frame(4,6);
        assertEquals(10,testFrame.getScore());
        assertTrue(testFrame.isSpare());
        assertFalse(testFrame.isStrike());
    }

    /*
    Might be overdone, but tests, if scores are added correctly. (In Fact this is not even
    part of TDD, because i programmed the "addScore" function before i created this test.)
     */
    @Test
    public void testScoreAddition(){
        Frame testFrame = new Frame(4,6);
        testFrame.addScore(4);
        assertEquals(14,testFrame.getScore());

    }
}