package org.studentengagementstatistics;

import java.util.*;
import java.time.LocalDate;

public class Student {
    private static int lastID;
    private static final Random rnd;
    private static final Map<Long, Student> registeredStudents;

    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean programmingExperience;

    private final Map<String, CourseEngagement> engagementMap;

    static {
        lastID = 1;
        rnd = new Random();
        registeredStudents = new HashMap<>();
    }

    public Student(String countryCode, int yearEnrolled,
                   int ageEnrolled, String gender, boolean programmingExperience) {
        this.studentId = lastID++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        engagementMap = new HashMap<>();
        registeredStudents.put(this.getStudentId(), this);
    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean isProgrammingExperience() {
        return programmingExperience;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }

    public long getMonthsSinceActive(String courseCode) {
        if (courseCode.isBlank()) return -1;

        CourseEngagement extractedObject = engagementMap.get(courseCode);

        if (extractedObject != null) {
           return extractedObject.getMonthsSinceActive();
        }

        return -1;
    }

    public long getMonthsSinceActive() {
        Collection<CourseEngagement> engValues = getEngagementMap().values();

        return engValues.stream()
                .map(CourseEngagement::getMonthsSinceActive)
                .mapToLong(i -> i)
                .summaryStatistics().getMin();
    }

    public double getPercentComplete(String courseCode) {
        if (courseCode.isBlank()) return -1.0;

        CourseEngagement extractedEng = engagementMap.get(courseCode);

        if (extractedEng != null) {
            return extractedEng.getPercentComplete();
        }

        return -1.0;
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    public boolean addCourse(Course course, LocalDate enrollDate) {
        CourseEngagement extractedEng = engagementMap.get(course.courseCode());

        if (extractedEng == null) {
            engagementMap.put(course.courseCode(),
                    new CourseEngagement(course, enrollDate,
                            "attending",1, enrollDate));
            return true;
        }

        return false;
    }

    public boolean addCourse(Course course) {
        return addCourse(course, LocalDate.now());
    }

    public boolean watchLecture(String courseCode, int lectureNumber, int month, int year) {
        if (month <= 0 || month > 12) return false;

        CourseEngagement extractedEngToBeProcess = engagementMap.get(courseCode);

        if (extractedEngToBeProcess != null) {
            if (extractedEngToBeProcess.getLastLecture() >= lectureNumber) return false;

            LocalDate dateForLectureWatching = LocalDate.of(year, month, 1);
            extractedEngToBeProcess.watchLecture(lectureNumber, dateForLectureWatching);
            return true;
        }

        return false;
    }

    public static Student getRandomStudent(Course... courses) {
        String[] countryCodes = {"RO", "GB", "DE", "FR", "BE"};
        String[] genderToBeEnrolledFrom = {"Male", "Female"};

        int chosenYear = rnd.nextInt(2000, 2023);

        Student newStudent = new Student(countryCodes[rnd.nextInt(0, countryCodes.length)],
                chosenYear,rnd.nextInt(17, 31),genderToBeEnrolledFrom[rnd.nextInt(genderToBeEnrolledFrom.length)],
                rnd.nextBoolean());

        for (Course course : courses) {
            LocalDate toBeUSedForEnrollment = LocalDate.of(chosenYear,
                    rnd.nextInt(1, 13), rnd.nextInt(1, 27));
            newStudent.addCourse(course, toBeUSedForEnrollment);

            newStudent.watchLecture(course.courseCode(), rnd.nextInt(1, course.lectureCount() + 1),
                    rnd.nextInt(1, 13), chosenYear);
        }

        for (Course c : courses) {
            int lecture = rnd.nextInt(1, c.lectureCount());
            int year = rnd.nextInt(newStudent.getYearEnrolled(), LocalDate.now().getYear() + 1);
            int month = rnd.nextInt(1, 13);

            if (year == (LocalDate.now().getYear() - 1) && (month > LocalDate.now().getMonthValue())) {
                    month = LocalDate.now().getMonthValue();
            }
            newStudent.watchLecture(c.courseCode(), lecture, month, year);
        }

        return newStudent;
    }

    public static Map<Long, Student> getRegisteredStudents() {
        return Map.copyOf(registeredStudents);
    }

    @Override
    public String toString() {
        return String.format("StudentID: %d, Country Code: %s, Year Enrolled: %d, Age Enrolled: %d, Gender: %s, Programming Experience: %s, " +
                        "Engagement Map: %s",
                studentId, countryCode, yearEnrolled, ageEnrolled, gender, programmingExperience, engagementMap);
    }
}
