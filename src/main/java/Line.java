import java.util.ArrayList;
import java.util.Scanner;

public class Line {
    private static final String STRIKE = "X";
    private static final char NO_SCORE = '-';
    private static final char SPARE = '/';
    private static final int STATUS_OK = 0;


    private ArrayList<Frame> frames = new ArrayList<>();
    private int lineScore = 0;


    public static void main(String[] args) {
        Line line = new Line();
        System.out.println("Wollen Sie die Frames einzelnd eingeben? [Y/N]");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            while (true) {
                System.out.println("Geben Sie einen Spielzug ein, oder drücken sie 'Q'," +
                        "um das Programm zu beenden.");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("Q")) {
                    System.exit(STATUS_OK);
                } else {
                    line.processFrame(parseInput(input));
                    line.calculateScore();
                    System.out.println("Ihre Aktuelle Punktzahl beträgt " + line.lineScore + " Punkte!");
                }
            }
        } else {
            System.out.print("Geben Sie die Line als Ganzes ein: ");
            line.processWholeLine(sc.nextLine());
        }
    }

    /**
     * calculates the score of a whole Bowling game.
     *
     * @param input the pins knocked over in every Frame i.e.: 37 5/ X 8- ...
     */
    public void processWholeLine(String input) {
        String[] inputWholeLine = input.split(" ");
        for (String rolls : inputWholeLine) {
            this.processFrame(parseInput(rolls));
        }
        this.calculateScore();
        System.out.println("Ihr Punktestand ist: " + this.lineScore);
    }

    /**
     * Adds frame to list, and calculates its score. Also takes in account frame interdependencies.
     *
     * @param scoreCurrentFrame the score of the two rolls of the entered frame
     */
    public void processFrame(int[] scoreCurrentFrame) {
        Frame currentFrame = new Frame(scoreCurrentFrame[0], scoreCurrentFrame[1]);
        frames.add(currentFrame);
        int frameNumber = frames.size() - 1;  //
        if (frameNumber >= 1 && frameNumber <= 9) { //no further score calculation for first frame special case for 11th
            //and 12th frame
            if (frames.get(frameNumber - 1).isStrike()) {
                //previous frame was strike
                frames.get(frameNumber - 1).addScore(currentFrame.getScore());
                if (frameNumber >= 2 && frames.get(frameNumber - 2).isStrike()) {
                    //last two frames were strikes.
                    frames.get(frameNumber - 2).addScore(currentFrame.getScore());
                }
            } else if (frames.get(frameNumber - 1).isSpare()) {
                //last frame was spare, hence add score of first roll to last frame
                frames.get(frameNumber - 1).addScore(scoreCurrentFrame[0]);
            }
        } else if (frameNumber == 10 && frames.get(frameNumber - 1).isStrike()) { //special case "frame 11"
            //10th frame was strike
            frames.get(frameNumber - 2).addScore(scoreCurrentFrame[0]);

        }
    }

    /**
     * converts the input, that may contain special characters like '/' or '-' into an int[] with the number of pins
     * knocked over in the two rolls of one frame.
     *
     * @param input the input String for one frame
     * @return an int[] array containing the number of pins that were knocked over
     */
    static int[] parseInput(String input) {
        if (input.equalsIgnoreCase(STRIKE)) {
            //strike: that means the first roll knocked all pins over.
            return new int[]{10, 0};
        } else if (input.length() == 2) {
            if (input.charAt(0) == NO_SCORE) {
                if (input.charAt(1) == NO_SCORE) {
                    //Gutter: that means both rolls are misses.
                    return new int[]{0, 0};
                } else if (input.charAt(1) == SPARE) {
                    //spare: that means the sum of the rolls is 10, but first rol is less than 10.
                    return new int[]{0, 10};
                } else {
                    //first roll == 0; second roll in 1-9
                    return new int[]{0, Character.getNumericValue(input.charAt(1))};
                }
            } else {    //first roll is in 1-9
                int roll1 = Character.getNumericValue(input.charAt(0)); //maybe hard to read, but shorter
                if (input.charAt(1) == NO_SCORE) {
                    //first roll in 1-9 and second roll: -
                    return new int[]{roll1, 0};
                } else if (input.charAt(1) == SPARE) {
                    //spare
                    return new int[]{roll1, 10 - roll1};
                } else {
                    //both rolls in 1-9
                    return new int[]{roll1, Character.getNumericValue(input.charAt(1))};
                }
            }
        } else if (input.length() == 1) { //this is a special case for the last roll if the 10th frame was a strike
            if (Character.getNumericValue(input.charAt(0)) >= 1 && Character.getNumericValue(input.charAt(0)) <= 9) {
                return new int[]{Character.getNumericValue(input.charAt(0)), 0};
            }
        }
        System.out.println("Ungültige Eingabe! Wird als 0 Punkte gewertet!"); //may be changed to throw an exception
        return new int[]{0, 0}; //This statement is only reached, if invalid input is given.
    }

    /**
     * Adds the score of all the frames to calculate the score of the whole line.
     */
    private void calculateScore() {
        lineScore = 0;
        for (Frame frame : frames) { //should I create a testcase for this function?
            lineScore += frame.getScore();
        }
    }


    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }

    public int getLineScore() {
        return lineScore;
    }
}
