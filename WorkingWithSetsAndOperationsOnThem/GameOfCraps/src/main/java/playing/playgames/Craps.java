package playing.playgames;

import java.security.SecureRandom;

public class Craps {
    private static int diceOne, diceTwo, sum = 0, yourPoint = 0;
    private static final SecureRandom rnd = new SecureRandom();
    private static int numberOfThrows = 0;

    private enum Status { CONTINUE, WON, LOST }

    private static void throwTheDices() {
        diceOne = (rnd.nextInt(6) + 1);
        diceTwo = (rnd.nextInt(6) + 1);

        numberOfThrows += 1;
        sum = diceOne + diceTwo;
    }

    private static void playTheGame() {
        String statusOfTheGame = Status.CONTINUE.name();

        while (statusOfTheGame.equals(Status.CONTINUE.name())) {
            throwTheDices();

            System.out.printf("%n%s%s%s%s", "Rolled ", diceOne, " and ", diceTwo);

            if (numberOfThrows != 1) {
                if (sum == yourPoint) {
                    System.out.printf("%n%s%s%s%s%s%s%s", "Number of throws: ", numberOfThrows, " First Dice: ", diceOne, " Second dice: ", diceTwo," - You Win!!!");
                    statusOfTheGame = Status.WON.name();
                } else if (sum == 7) {
                    System.out.printf("%n%s%s%s%s%s%s%s", "Number of throws: ", numberOfThrows, " First Dice: ", diceOne, " Second dice: ", diceTwo," - You Lose!!!");
                    statusOfTheGame = Status.LOST.name();
                }
            } else {     // this else structure will be parsed only once after the first throw. This can be written with switch but there are more lines of code.
                if ((sum == 7) || (sum == 11)) {
                    System.out.printf("%n%s%s%s%s%s%s%s", "Number of throws: ", numberOfThrows ," First Dice: ", diceOne, " Second dice: ", diceTwo," - You Win!!!");
                    statusOfTheGame = Status.WON.name();
                } else if ((sum == 2) || (sum == 3) || (sum == 12)) {
                    System.out.printf("%n%s%s%s%s%s%s%s", "Number of throws: ", numberOfThrows, " First Dice: ", diceOne, " Second dice: ", diceTwo," - You Lose, Craps!!!");
                    statusOfTheGame = Status.LOST.name();
                } else {
                    yourPoint = sum;
                    System.out.printf("%n%s%s%s%s", "You make your point: ", yourPoint, " Number of throws: ", numberOfThrows);
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        playTheGame();
    }
}

