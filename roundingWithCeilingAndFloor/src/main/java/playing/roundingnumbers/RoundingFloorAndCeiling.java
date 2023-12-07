package WithJava.roundingWithCeilingAndFloor.src.main.java.playing.roundingnumbers;

public class RoundingFloorAndCeiling {
    private static int myFloor(double num) {
        return (int) num;
    }

    private static int myCeil(double num) {
        int valueToReturn;

        if (num % 1 == 0) {
            valueToReturn = (int) num;
        } else {
            valueToReturn = (int) num + 1;
        }

        return valueToReturn;
    }

    public static void main(String[] args) {
        System.out.println(myCeil(4.2));
        System.out.println(myFloor(3.9));
    }
}
