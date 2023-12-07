package playing.university;

import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.ArrayList;

record Course(String courseId, String name, String subject) {}

record Purchase(String courseId, int studentId, double price, int year, int dayOfYear) {
    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(year, dayOfYear);
    }
}

public class Student implements Comparable<Student> {
    public static int lastID;

    private String name;
    private int id;
    private List<Course> listOfCourses;

    static {
        lastID = 1;
    }

    public Student(String name, List<Course> listOfCourses) {
        this.name = name;
        this.listOfCourses = listOfCourses;
        this.id = lastID++;
    }

    public Student(String name, Course course) {
        this(name, new ArrayList<>(Arrays.asList(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(Course course) {
        listOfCourses.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames = new String[listOfCourses.size()];
        Arrays.setAll(courseNames, i -> listOfCourses.get(i).name());

        return String.format("[%d] : %s", id, String.join(", ", courseNames));
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(o.getId(), this.getId());
    }
}
