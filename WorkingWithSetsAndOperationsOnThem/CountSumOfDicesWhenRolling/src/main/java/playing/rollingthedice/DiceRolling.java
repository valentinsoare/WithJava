package playing.rollingthedice;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DiceRolling {
    private static final Random rnd = new Random();

    private static List<Integer> rollTheDice() {
        return new ArrayList<>(List.of(rnd.nextInt(6) + 1, rnd.nextInt(6) + 1));
    }

    private static List<Integer> implementRolling(int howManyTimesToRoll) {
        List<Integer> generatedValues;
        int firstFace, secondFace, getValue, j = 0, index;
        List<List<Integer>> countDices = new ArrayList<>();
        List<Integer> sumOfDices = new ArrayList<>(Collections.nCopies(13, 0));

        while (j < 6) {
            countDices.add(Arrays.asList(0, 0, 0, 0, 0, 0));
            j++;
        }

        for (int i = 0; i < howManyTimesToRoll; i++) {
            generatedValues = rollTheDice();

            firstFace = generatedValues.get(0);
            secondFace = generatedValues.get(1);
            getValue = countDices.get(firstFace - 1).get(secondFace - 1);

            countDices.get(firstFace - 1).set(secondFace - 1, (getValue + 1));

            index = (firstFace + secondFace);
            sumOfDices.set(index, (sumOfDices.get(index) + 1));
        }

        return sumOfDices;
    }

    public static void printResult(List<Integer> toBeUsed) {
        System.out.printf("%n%s%n", "-".repeat(27));

        System.out.printf(" ".repeat(4));
        for (int z = 1; z <= 6; z++) {
            System.out.printf("%-4s", z);
        }

        System.out.println();

        for (int i = 1; i <= 6; i++) {
            System.out.printf("%-4s", i);
            for (int j = 1; j <= 6; j++) {
                for (int k = 2; k < toBeUsed.size(); k++) {
                    if ((j + i) == k) {
                        System.out.printf("%-4s", toBeUsed.get(k));
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    public static void printingHeader(String givenMessage) throws InterruptedException {
        StringBuilder valueToReturn = new StringBuilder();
        String errorMessage = String.format("%n%s%n", "ERROR please use a valid message with at least two words!");
        List<String> temp;


        if (givenMessage.isBlank()) {
            System.out.printf("%s", errorMessage);
            TimeUnit.SECONDS.sleep(1);
        } else {
            temp = new ArrayList<>(Arrays.asList(givenMessage.toLowerCase().trim().split(" ")));

            if (temp.size() < 2) {
                System.out.printf("%s", errorMessage);
                TimeUnit.SECONDS.sleep(1);
            } else {
                valueToReturn.append(" ");

                for (String s : temp) {
                    valueToReturn.append(Character.toTitleCase(s.charAt(0))).append(s.substring(1)).append(" ");
                }
            }
        }

        String lineToPrint = String.format("%s", "-".repeat(27));


        System.out.printf("%s%s%n","\n".repeat(2), lineToPrint);
        System.out.printf("%s", valueToReturn);
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> listWithSumDicesFaces = implementRolling(360);

        printingHeader("count dices when rolling");
        printResult(listWithSumDicesFaces);
    }
}

