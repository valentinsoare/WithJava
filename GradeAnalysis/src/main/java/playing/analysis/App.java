package playing.analysis;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {
    private static StringBuilder processedMessage;
    private static String inputFromUser = "";
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Double> grades = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> frequency = new ArrayList<>();

    private static void creatingPrintingHeader(String message) {
        processedMessage = new StringBuilder();
        ArrayList<String> messageAsList = new ArrayList<>(Arrays.asList(message.split(" ")));

        processedMessage.append("#   ");

        for (String word : messageAsList) {
            for (int i = 0; i < word.length(); i++) {
                if (i == word.length() - 1) {
                    processedMessage.append(word.toUpperCase().charAt(i));
                } else {
                    processedMessage.append(word.toUpperCase().charAt(i)).append(" ");
                }
            }
            processedMessage.append(" ".repeat(3));
        }

        processedMessage.append("#");
        String lineToBePrinted = String.format("%s", "-".repeat(processedMessage.length()));

        System.out.printf("%n%s%s%n", " ".repeat(processedMessage.length() / 2), lineToBePrinted);
        System.out.printf("%s%s", " ".repeat(processedMessage.length() / 2), processedMessage);
        System.out.printf("%n%s%s", " ".repeat(processedMessage.length() / 2), lineToBePrinted);
    }

    private static String checkActions(String givenAnswer, boolean forExceptions) throws InterruptedException {
        if (givenAnswer.equalsIgnoreCase("quit")) {
            System.out.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), "Quitting...");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.exit(0);
        } else if (givenAnswer.equalsIgnoreCase("back")) {
            if (forExceptions) {
                System.err.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), "ERROR on the first question you don't have where to go back!");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("\033[H\033[2J");
                System.out.flush();
            } else {
                System.out.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), "Going back...");
                TimeUnit.SECONDS.sleep(2);
                return "true";
            }
        }
        return "false";
    }

    private static void catchGradesForAnalysis(boolean howManyGrades) throws InterruptedException {
        int numberOfGrades = -1;
        ArrayList<String> questions = new ArrayList<>(Arrays.asList("How many grades to give for analysis (quit): ",
                "Please provide grades separated by a comma (quit/back): "));

        while (grades.size() != numberOfGrades) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            creatingPrintingHeader("poll analysis");

            if (howManyGrades) {
                System.out.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), questions.get(0));
            } else {
                System.out.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), questions.get(1));
            }

            inputFromUser = input.nextLine();

            if (Boolean.parseBoolean(checkActions(inputFromUser, howManyGrades))) {
                howManyGrades = true;
            } else {
                if (howManyGrades) {
                    try {
                        numberOfGrades = Integer.parseInt(inputFromUser);
                        if (numberOfGrades <= 0) {
                            System.err.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), "ERROR for number of grades please use an integer greater than zero!");
                            TimeUnit.SECONDS.sleep(2);
                        } else {
                            howManyGrades = false;
                        }
                    } catch (NumberFormatException e) {
                        System.err.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), "ERROR for number of grades please use an integer!");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } else {
                    ArrayList<String> partialListOfGrades = new ArrayList<>(Arrays.asList(inputFromUser.trim().split(",")));

                    for (String partialListOfGrade : partialListOfGrades) {
                        grades.add(Double.parseDouble(partialListOfGrade.trim()));
                    }

                    if (grades.size() != numberOfGrades) {
                        System.err.printf("%n%s%s", " ".repeat(processedMessage.length() / 4), "ERROR please enter the same amount of grades that you mentioned!");
                        grades = new ArrayList<>();
                        TimeUnit.SECONDS.sleep(2);
                    }
                }
            }
        }
    }

    private static void countGrades() {
        boolean exists;
        double counting;

        for (Double grade : grades) {
            exists = false;
            for (ArrayList<Double> doubles : frequency) {
                if (grade.equals(doubles.get(0))) {
                    counting = doubles.get(1) + 1;
                    doubles.set(1, counting);
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                frequency.add(new ArrayList<>(Arrays.asList(grade, 1.0)));
            }
        }
    }

    private static void sortAndPrintingGradesAndFrequency() {
        System.out.printf("%n%s%s%n", " ".repeat(processedMessage.length() / 2), "We have the following grades and frequency: ");
        System.out.printf("%s%s%20s%n", " ".repeat(processedMessage.length() / 2), "Grade", "Frequency");

        ArrayList<Double> tmp;

        for (int i = 0; i < frequency.size() - 1; i ++) {
            if (frequency.get(i).get(1) < frequency.get(i+1).get(1)) {
                tmp = frequency.get(i);
                frequency.set(i, frequency.get(i + 1));
                frequency.set(i+1, tmp);
            }
        }

        for (ArrayList<Double> grade : frequency) {
            System.out.printf("%s%-16" + (grade.get(0) % 1 == 0 ? ".0f" : ".1f") +"%.0f%n",
                    " ".repeat(processedMessage.length() / 2), grade.get(0), grade.get(1));
        }
    }
    public static void main(String[] args) throws InterruptedException {
        catchGradesForAnalysis(true);
        countGrades();
        sortAndPrintingGradesAndFrequency();
    }
}
