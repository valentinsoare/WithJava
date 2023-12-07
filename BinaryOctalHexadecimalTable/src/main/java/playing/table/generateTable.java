package WithJava.BinaryOctalHexadecimalTable.src.main.java.playing.table;

import java.util.List;
import java.util.ArrayList;

public class generateTable {
    private static String convertFromDecimalToOctal(int givenNumber) {
        int digit;
        StringBuilder numberToReturned = new StringBuilder();

        while (givenNumber != 0) {
            digit = givenNumber % 8;
            givenNumber /= 8;

            numberToReturned.append(String.valueOf(digit));
        }

        return numberToReturned.reverse().toString();
    }

    private static String convertFromDecimalToBinary(int givenNumber) {
        int power = 0, calc;
        List<Integer> powerOfTwo = new ArrayList<>();
        StringBuilder binaryFormat = new StringBuilder();

        while (true) {
            calc = (int) Math.pow(2, power);

            if (calc <= givenNumber) {
                powerOfTwo.add(calc);
                power++;
            } else {
                break;
            }
        }

        for (int i = powerOfTwo.size() - 1; i >= 0; i--) {
            if (givenNumber >= powerOfTwo.get(i)) {
                givenNumber -= powerOfTwo.get(i);
                binaryFormat.append("1");
            } else {
                binaryFormat.append("0");
            }
        }

        return binaryFormat.toString();
    }

    private static String convertFromDecimalToHexadecimal(int givenNumber) {
        int digit, toCompare;
        char[] neededLetters = "ABCDEF".toCharArray();
        StringBuilder toBeReturned = new StringBuilder();

        while (givenNumber != 0) {
            digit = givenNumber % 16;
            givenNumber /= 16;

            toCompare = (digit - 10);

            if ((toCompare >= 0) && (toCompare <= 5)) {
                toBeReturned.append(neededLetters[digit - 10]);
            } else {
                toBeReturned.append(digit);
            }
        }

        return toBeReturned.reverse().toString();
    }

    private static void printTable(int limit) {
        System.out.printf("%n%sDECIMAL %s OCTAL %s HEXADECIMAL %s BINARY%n", " ".repeat(5), " ".repeat(5), " ".repeat(3), " ".repeat(3));
        System.out.printf("%s%s%n", " ".repeat(5), "-".repeat(46));

        for (int i = 1; i < limit; i++) {
            System.out.printf("%12s%12s%16s%11s%n", i, convertFromDecimalToOctal(i), convertFromDecimalToHexadecimal(i), convertFromDecimalToBinary(i));
        }
    }

    public static void main( String[] args ) {
        printTable(256);
    }
}
