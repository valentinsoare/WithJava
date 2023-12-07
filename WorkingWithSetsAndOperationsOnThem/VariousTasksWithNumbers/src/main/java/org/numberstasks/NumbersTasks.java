package org.numberstasks;

import java.util.*;

public class NumbersTasks {

    private static Random rnd = new Random();
    private static Scanner input = new Scanner(System.in);

    private static boolean isPalindrome(String givenInput) {
        boolean valueToReturn;

        if (givenInput.length() <= 1) {
            valueToReturn = true;
        } else if (givenInput.charAt(0) != givenInput.charAt(givenInput.length() - 1))  {
            valueToReturn = false;
        } else {
            return isPalindrome(givenInput.substring(1, (givenInput.length() - 1)));
        }

        return valueToReturn;
    }

    private static boolean isPerfect(int givenNumber, List<Integer> factors, int toDivideBy) {
            if (givenNumber % toDivideBy == 0) {
                factors.add(toDivideBy);
            }

            if (toDivideBy == (givenNumber - 1)) {
                int sum = 0;

                for (int element : factors) {
                    sum += element;
                }

                return (sum == givenNumber);
            }

            return isPerfect(givenNumber, factors, toDivideBy + 1);
    }

    private static boolean isPrime(int givenNumber) {
        if (givenNumber <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(givenNumber); i++) {
            if (givenNumber % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static List<Integer> findPrimes(int limit) {
        List<Integer> allPrimes = new LinkedList<>();

        for (int i = 2; i < limit; i++) {
            if (isPrime(i)) {
                allPrimes.add(i);
            }
        }

        return allPrimes;
    }

    private static int calculateSumOfDigits(int givenNumber, int sum) {
        if (givenNumber == 0) {
            return sum;
        } else {
            sum += (givenNumber % 10);
            givenNumber /= 10;
        }

        return calculateSumOfDigits(givenNumber, sum);
    }

    private static int findGCD(int firstNumber, int secondNumber) {
        int gcd = 0;
        int limit = Math.min(firstNumber, secondNumber);

        for (int i = 1; i < limit; i++) {
            if ((firstNumber % i == 0) && (secondNumber % i == 0)) {
                gcd = i;
            }
        }

        return gcd;
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(isPalindrome("1221"));
//        System.out.println(isPerfect(12, new ArrayList<>(), 1));
//
//        System.out.println(isPrime(4));
//        System.out.println(findPrimes(10_000));

//        System.out.println(calculateSumOfDigits(1919872, 0));

//        System.out.println(findGCD(12, 18));


    }
}
