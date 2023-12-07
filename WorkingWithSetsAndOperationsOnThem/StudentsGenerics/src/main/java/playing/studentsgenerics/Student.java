package playing.studentsgenerics;

import java.security.SecureRandom;

public class Student implements QueryItem, Comparable<Student> {
    private static int LAST_ID = 1_000;
    private int studentId;
    private String name;
    private String course;
    private Integer yearStarted;
    protected static SecureRandom rnd = new SecureRandom();

    private enum firstNames { Ann, Bill, Cathy, John, Tim }
    private enum courses { Cpp, Java, Python }
    private Integer namesIndex;
    private Integer coursesIndex;

    public Student() {
        int lastNameIndex = rnd.nextInt(26) + 65;
        this.name = firstNames.values()[rnd.nextInt(firstNames.values().length)] + " " + (char) lastNameIndex;
        this.course = String.valueOf(courses.values()[rnd.nextInt(courses.values().length)]);
        this.yearStarted = rnd.nextInt(9) + 2015;
        this.namesIndex = 0;
        this.coursesIndex = 0;
        this.studentId = LAST_ID++;
    }

    public String parseCourses() {
        return String.valueOf(courses.values()[(coursesIndex++ % courses.values().length)]);
    }

    public String parseNames() {
        return String.valueOf(firstNames.values()[(namesIndex++ % firstNames.values().length)]);
    }

    public firstNames[] getFirstNames() {
        return firstNames.values();
    }

    public String getName() {
        return this.name;
    }

    public String getCourse() {
        return course;
    }

    public courses[] getListOfCourses() {
        return courses.values();
    }

    public Integer getYearStarted() {
        return yearStarted;
    }

    @Override
    public String toString() {
        return String.format("%-8d%-10s%-10s%d", studentId, name, course, yearStarted);
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        String fName = fieldName.toUpperCase();

        return switch(fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == Integer.parseInt(value);
            case "STUDENTID" -> studentId == Integer.parseInt(value);
            default -> false;
        };
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.studentId, o.studentId);
    }
}
