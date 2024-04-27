import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrameTest {
    @Test
    public void testFrameConstructor() {
        Frame testFrame = new Frame(4,6);
        assertEquals(10,testFrame.getScore());

    }
}