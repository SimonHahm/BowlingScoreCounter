public class Frame {
    private static final int NUMBER_OF_PINS = 10;

    private int score;
    private boolean strike = false;
    private boolean spare = false;

    /**
     * Creates a new Frame, and needs the number of pins knocked over.
     * @param throw1 number of pins knocked over in the first throw
     * @param throw2 number of pins knocked over in the second throw
     */
    Frame(int throw1, int throw2) {
        score = throw1 + throw2;
        if (throw1 == NUMBER_OF_PINS) {
            strike = true;
        } else if (throw1 + throw2 == NUMBER_OF_PINS) {
            spare = true;
        }
    }

    /**
     * Returns the current score of the frame.
     * @return the current score of the frame
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds score to a frame i.e. if Frame was a strike or a spare.
     * @param addedScore The score that is added to this frame due to later throws
     */
    public void addScore(int addedScore) {
        this.score += addedScore;
    }

    /**
     * Shows, if current frame is a spare.
     * @return boolean, that shows if the frame is a spare
     */
    public boolean isSpare() {
        return spare;
    }

    /**
     * Shows, if current frame is a strike.
     * @return boolean, that shows if the frame is a strike
     */
    public boolean isStrike() {
        return strike;
    }
}
