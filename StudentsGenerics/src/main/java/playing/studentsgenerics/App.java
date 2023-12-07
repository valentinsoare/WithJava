package WithJava.StudentsGenerics.src.main.java.playing.studentsgenerics;

import java.util.List;
import java.util.ArrayList;

public class App {
    public static void printStudents(List <? extends Student> students) {
        for (var student : students) {
            System.out.println(student);
        }
    }

    public static void printing(List<? extends Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void anotherPrintStudents(List<? super ComputerScienceStudent> students) {
        for (Object s : students) {
            System.out.println(s);
        }
    }

    public static void main( String[] args ) {
        int countStudents = 10;
        List<ComputerScienceStudent> students = new ArrayList<>();

        while (countStudents > 0) {
            students.add(new ComputerScienceStudent());
            countStudents--;
        }

        printStudents(students);
        anotherPrintStudents(students);

        var listToBeQuery = new QueryList<>(students);

        System.out.println("\n");
        printStudents(listToBeQuery.getMatches("course", "java"));

        System.out.println("\n");
        printStudents(QueryList.<ComputerScienceStudent>getMatches(students, "yearstarted", "2018"));

    }
}
