package WithJava.GradeBook.src.main.java.playing.gradesregistered;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GradeBook {
    private static StringBuilder processedMessage;

    private final String nameOfCourse;
    private ArrayList<Double> allGrades;;
    private ArrayList<ArrayList<String>> grades;

    private static String validateNameOfCourse(String nameOfCourse) throws InterruptedException {
        String valueToReturn = null;

        if ((nameOfCourse.isBlank()) || (nameOfCourse.length() < 4)) {
            System.err.printf("%n%s", "ERROR - please use a valid name for this course, use a combination of alnum characters with 4 chars or more!");
            TimeUnit.SECONDS.sleep(2);
        } else {
            valueToReturn = StringUtils.capitalize(nameOfCourse);
        }

        return valueToReturn;
    }

    public GradeBook(String nameOfCourse) throws InterruptedException {
        this.nameOfCourse = validateNameOfCourse(nameOfCourse);
        this.grades = new ArrayList<>();
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    private void drawingHeader() {
        processedMessage = new StringBuilder();
        String message = String.format("%s %s", "Grade Book", getNameOfCourse());
        ArrayList<String> messageAsList = new ArrayList<>(Arrays.asList(message.split(" ")));

        processedMessage.append("#   ");

        for (String word : messageAsList) {
            for (int i = 0; i < word.length(); i++) {
                if (i == word.length() - 1) {
                    processedMessage.append(word.charAt(i));
                } else {
                    processedMessage.append(word.charAt(i)).append(" ");
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

    public void askForGrades() throws InterruptedException {
        String valueFromUser = "";
        Scanner input = new Scanner(System.in);
        ArrayList<String> tempArrayList;

        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            drawingHeader();

            System.out.printf("%n%s%s", " ".repeat(processedMessage.length() / 2), "Please provide name of the student, then a comma and a grade (quit): ");
            valueFromUser = input.nextLine();

            if (valueFromUser.equalsIgnoreCase("quit")) {
                System.out.printf("%n%s%s", " ".repeat(processedMessage.length() / 2), "Quitting...");
                TimeUnit.SECONDS.sleep(1);
                System.exit(0);
            } else {
                tempArrayList = new ArrayList<>(Arrays.asList(valueFromUser.split(",")));

                tempArrayList.replaceAll(String::trim);

                if (tempArrayList.size() < 2) {
                    System.err.printf("%n%s%s", " ".repeat(processedMessage.length() / 2), "ERROR please provide a student name and then grades each separated by a comma!");
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    break;
                }
            }
        }

        boolean exists = false;

        for (ArrayList<String> grade : grades) {
            if (grade.get(0).equalsIgnoreCase(tempArrayList.get(0))) {
                grade.addAll(new ArrayList<>(tempArrayList.subList(1, tempArrayList.size())));
                exists = true;
                break;
            }
        }

        if (!exists) {
            grades.add(tempArrayList);
        }
    }

    private void findMaxMinGrade() {
        allGrades = new ArrayList<>();
        ArrayList<String> studentsWithMax = new ArrayList<>(), studentsWithMin = new ArrayList<>();

        for (ArrayList<String> grade : grades) {
            for (int i = 1; i < grade.size(); i++) {
                allGrades.add(Double.valueOf(grade.get(i)));
            }
        }

        allGrades.sort(Comparator.naturalOrder());
        double max = allGrades.get(allGrades.size() - 1);
        double min = allGrades.get(0);


        for (ArrayList<String> gradeToCheck : grades) {
            for (int j = 1; j < gradeToCheck.size(); j++) {
                if ((Double.parseDouble(gradeToCheck.get(j)) == max) &&
                        (!studentsWithMax.contains(gradeToCheck.get(0)))) {
                    studentsWithMax.add(gradeToCheck.get(0));
                }

                if ((Double.parseDouble(gradeToCheck.get(j)) == min) &&
                        (!studentsWithMin.contains(gradeToCheck.get(0)))) {
                    studentsWithMin.add(gradeToCheck.get(0));
                }
            }
        }

        System.out.printf("%n%s%" + ((max % 1 == 0) ? ".0f" : ".1f") + "%s%s%n%s%" + ((min % 1 == 0) ? ".0f" : ".1f") +
                        "%s%s", "Max Grade: ", max , " for students: ", studentsWithMax, "Min Grade: ", min, " for Students: ", studentsWithMin);
    }

    public void drawingChart() {
        System.out.printf("%n%n%s", "Counting all grades: ");
        System.out.printf("%n%s", "-".repeat(37));
        System.out.printf("%n%-10s%s", "Grade", "Frequency");

        for (int i = 0; i < allGrades.size() - 1; i++) {
            if (!Objects.equals(allGrades.get(i), allGrades.get(i + 1))) {
                System.out.printf("%n%-6s |%s%s", allGrades.get(i), " ".repeat(2), "#".repeat(Collections.frequency(allGrades, allGrades.get(i))));
            }
        }

        System.out.printf("%n%-6s |%s%s", allGrades.get(allGrades.size() - 1), " ".repeat(2), "#".repeat(Collections.frequency(allGrades, allGrades.get(allGrades.size() - 1))));
    }

    public void calculateAveragePerStudent() {
        System.out.printf("%n%n%s", "Average per student: ");
        System.out.printf("%n%s", "-".repeat(37));

        double calc;
        double total = 0;

        for (ArrayList<String> grade : grades) {
            total = 0;

            for (int i = 1; i < grade.size(); i++) {
                total += Double.parseDouble(grade.get(i));
            }

            calc = (total / (grade.size() - 1));
            System.out.printf("%n%s%s%" + (calc % 1 == 0 ? ".0f" : ".1f"), grade.get(0), " ".repeat(5), calc);
        }
    }

    public void calculateStatsOnGrades() {
        findMaxMinGrade();
        drawingChart();
        calculateAveragePerStudent();
    }

    public List<ArrayList<String>> getGrades() {
        return grades;
    }

    public List<Double> getAllGrades() {
        return allGrades;
    }

    public List<String> getStudents() {
        LinkedList<String> studentsForThisCourse = new LinkedList<>();

        for (ArrayList<String> grade : grades) {
            studentsForThisCourse.push(grade.get(0));
        }

        return studentsForThisCourse;
    }
}
