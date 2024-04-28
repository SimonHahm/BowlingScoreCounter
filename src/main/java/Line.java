import java.util.ArrayList;
import java.util.Scanner;

public class Line {
    private ArrayList<Frame> Frames = new ArrayList<>();

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
        } else if (frameNumber == 10) { //if the ninth frame is a strike, the score of the 11th frame is added.
            if (frames.get(frameNumber - 2).isStrike()) {
                frames.get(frameNumber - 2).addScore(currentFrame.getScore());
            }
        }
    }

    static int[] parseInput(String input) {
        if (input.equalsIgnoreCase("X")) {
            //strike: that means the first roll knocked all pins over.
            return new int[]{10, 0};
        } else if (input.length() == 2) {
            if (input.charAt(0) == '-') {
                if (input.charAt(1) == '-') {
                    //Gutter: that means both rolls are misses.
                    return new int[]{0, 0};
                } else if (input.charAt(1) == '/') {
                    //spare: that means the sum of the rolls is 10, but first rol is less than 10.
                    return new int[]{0, 10};
                } else {
                    //first roll == 0; second roll in 1-9
                    return new int[]{0, Character.getNumericValue(input.charAt(1))};
                }
            } else {    //first roll is in 1-9
                int roll1 = Character.getNumericValue(input.charAt(0)); //maybe hard to read, but shorter
                if (input.charAt(1) == '-') {
                    //first roll in 1-9 and second roll: -
                    return new int[]{roll1, 0};
                } else if( input.charAt(1) == '/') {
                    //spare
                    return new int[]{roll1, 10 - roll1};
                } else {
                    //both rolls in 1-9
                    return new int[]{roll1, Character.getNumericValue(input.charAt(1))};
                }
            }
        }
        System.out.println("Invalid input. Is counted as no score"); //may be changed to throw an exception
        return new int[]{0, 0}; //This statement is only reached, if invalid input is given.
    }

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
