package WithJava.GuessTheNumber.src.main.java.org.guessinggame;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GuessTheNumber {
    private static int numberChosen;
    private static int numberOfTries = 0;
    private static String valueFromUser = "";
    private static Random rnd = new Random();
    private static Scanner input  = new Scanner(System.in);
    private static String errorMessage = String.format("%n%sERROR please use an integer or q to quit!", " ".repeat(2));

    private static void printHeader(String message) {
        StringBuilder stringToBePrinted = new StringBuilder();

        for (String word : new ArrayList<>(List.of(message.split(" ")))) {
            if (!word.isBlank()) {
                for (int i = 0; i < word.length(); i++) {
                    if (i != rnd.nextInt(word.length())) {
                        stringToBePrinted.append(Character.toTitleCase(word.charAt(i)));
                    } else {
                        stringToBePrinted.append(word.charAt(i));
                    }
                }
                stringToBePrinted.append(" ");
            }
        }

        String header = String.format("%s*  %s *", " ".repeat(16), stringToBePrinted);
        String line = String.format("%s", "-".repeat(header.length()));

        System.out.printf("%n%s", header);
        System.out.printf("%n%s%s", " ".repeat(8), line);
    }

    private static void validateNumberGuessed(int numberToBeGuessed, int numberToAnalyze, int end) throws InterruptedException {
        if (numberToAnalyze < numberToBeGuessed) {
            System.out.printf("%n%s To low. Try again!", " ".repeat(2));
            numberOfTries++;
        } else if (numberToAnalyze > numberToBeGuessed) {
            System.out.printf("%n%s To high. Try again!", " ".repeat(2));
            numberOfTries++;
        } else {
            System.out.printf("%n%s Congrats!", " ".repeat(2));

            if (numberOfTries == 10) {
                System.out.printf("%s Aha! You know the secret!", " ".repeat(2));
            } else if (numberOfTries < 10) {
                System.out.printf("%s Either you know the secret or you got lucky!", " ".repeat(2));
            } else {
                System.out.printf("%s You should be able to do better!", " ".repeat(2));
            }

            TimeUnit.SECONDS.sleep(2);

            while (!valueFromUser.equals("start_again")) {
                System.out.println("\033[H\033[2J");
                System.out.flush();

                printHeader("guess the number");

                System.out.printf("%n%s You want to guess again ? (yes/no): ", " ".repeat(2));
                valueFromUser = input.nextLine();

                switch (valueFromUser) {
                    case "yes" -> {
                        System.out.printf("%n%s Game is starting again....", " ".repeat(2));
                        TimeUnit.SECONDS.sleep(2);
                        valueFromUser = "start_again";
                    }
                    case "no" -> {
                        System.out.printf("%n%sExiting....", " ".repeat(2));
                        TimeUnit.SECONDS.sleep(1);
                        valueFromUser = "quiting";
                    }
                    default -> {
                        System.out.printf("%n%s ERROR please choose yes or no!", " ".repeat(2));
                        TimeUnit.SECONDS.sleep(2);
                    }
                }
            }
        }
    }

    private static void printMenuAndAskForNumber(int end, int numberToBeGuessed) throws InterruptedException {
        System.out.printf("%n%s Please guess the number between 1 and %s (tries %s, q to quit): ", " ".repeat(1), end, numberOfTries);
        valueFromUser = input.nextLine();

        if ((Character.isLetter(valueFromUser.charAt(0))) && (valueFromUser.length() > 1)) {
            System.out.println(errorMessage);
            TimeUnit.SECONDS.sleep(2);
        } else if (valueFromUser.equals("q")) {
            System.out.printf("%n%sExiting....%n", " ".repeat(2));
            TimeUnit.SECONDS.sleep(1);
            System.exit(0);
        } else {
            try {
                numberChosen = Integer.parseInt(valueFromUser);
                validateNumberGuessed(numberToBeGuessed, numberChosen, end);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

    private static void runGuessingGame(int end) throws InterruptedException {
        int numberToBeGuessed = rnd.nextInt(end) + 1;

        while (!valueFromUser.equals("quiting")) {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            printHeader("guess the number");
            printMenuAndAskForNumber(end, numberToBeGuessed);

            if (valueFromUser.equals("start_again")) {
                numberToBeGuessed = rnd.nextInt(end) + 1;
            }
        }
    }

    public static void main( String[] args ) throws InterruptedException {
        runGuessingGame(5);
    }
}
