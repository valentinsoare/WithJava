package playing.leapyear;

import java.util.Scanner;

public class CheckLeapYear {
    private static String isLeapYear(int givenYear) {
        if ((givenYear % 4 == 0 && givenYear % 100 == 0 && givenYear % 400 == 0) ||
                (givenYear % 4 == 0 && givenYear % 100 != 0)) {
            return "leap year!";
        } else {
            return "not a leap year!";
        }
    }

    private static void askForYear() {
        Scanner input = new Scanner(System.in);
        String valueFromUser = "";
        int valueToReturn = 0;

        while (true) {
            System.out.printf("%n%s_provide the year to check (q to quit): ", " ".repeat(5));
            valueFromUser = input.nextLine().trim();

            if (valueFromUser.equals("q")) {
                System.out.printf("%n%s Exiting...%n", " ".repeat(5));
                break;
            }

            try {
                valueToReturn = Integer.parseInt(valueFromUser);
                System.out.printf("%n %s %s is %s%n", " ".repeat(5), valueToReturn, isLeapYear(valueToReturn));
            } catch (NumberFormatException e) {
                System.err.printf("%sERROR please use an integer as an year!%n%n", " ".repeat(5));
            }

        }
    }

    public static void main(String[] args) {
        askForYear();
    }
}
