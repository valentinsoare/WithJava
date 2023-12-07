package WithJava.ComputerAssistedInstructionMathQuizes.src.main.java.playing.mathquestions;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public final class Player {
    private final int age;
    private final int emptySpaces;
    private final String firstName;
    private final String lastName;
    private final List<List<Integer>> numberOfTries;

    private static String validateNames(String firstName, int emptySpaces) throws InterruptedException {
        String errorMessage = String.format("%n\033[31m%s%s\033[0m%n", " ".repeat(emptySpaces),"ERROR please provide a valid first name, longer than two chars!");
        String nameProcessed = StringUtils.capitalize(firstName.toLowerCase());

        if ((nameProcessed.isBlank()) || (nameProcessed.length() <= 2)) {
            System.out.printf("%s", errorMessage);
            TimeUnit.SECONDS.sleep(1);
        }

        return nameProcessed;
    }

    private static int validateAge(int age, int emptySpaces) throws InterruptedException {
        String errorMessage = String.format("%n\033[31m%s%s\033[0m%n", " ".repeat(emptySpaces), "ERROR please use a valid age, a positive integer greater than 4!");

        if (age <= 0) {
            System.out.printf("%s", errorMessage);
            TimeUnit.SECONDS.sleep(1);
            age = -1;
        }

        return age;
    }

    private static int validateEmptySpaces(int emptySpaces) throws InterruptedException {
        String errorMessage = String.format("%n\033[31m%s%s\033[0m%n", " ".repeat(2), "ERROR please use a valid integer greater or equal to zero for empty spaces!");

        if (emptySpaces < 0) {
            System.out.printf("%s", errorMessage);
            TimeUnit.SECONDS.sleep(1);
            emptySpaces = 2;
        }

        return emptySpaces;
    }

    public Player(Player player) {
        this.firstName = player.firstName;
        this.lastName = player.lastName;
        this.age = player.age;
        this.emptySpaces = player.emptySpaces;
        this.numberOfTries = player.getNumberOfTries();
    }

    public Player(String firstName, String lastName, int age, int emptySpaces) throws InterruptedException {
        this.firstName = validateNames(firstName, emptySpaces);
        this.lastName = validateNames(lastName, emptySpaces);
        this.age = validateAge(age, emptySpaces);
        this.emptySpaces = validateEmptySpaces(emptySpaces);
        this.numberOfTries = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getEmptySpaces() {
        return emptySpaces;
    }

    public List<List<Integer>> getNumberOfTries() {
        return List.copyOf(numberOfTries);
    }
}
