import java.util.ArrayList;

public class Line {
    private ArrayList<Frame> Frames = new ArrayList<>();

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            //Frame f = new Frame();
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
                    return new int[]{roll1, 10-roll1};
                } else {
                    //both rolls in 1-9
                    return new int[]{roll1, Character.getNumericValue(input.charAt(1))};
                }
            }
        }
        System.out.println("Invalid input. Is counted as no score"); //may be changed to throw an exception
        return new int[]{0, 0}; //This statement is only reached, if invalid input is given.
    }
}
