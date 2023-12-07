package org.studentengagementstatistics;

import java.time.Month;
import java.time.Period;
import java.time.LocalDate;

public class CourseEngagement {
    private final Course course;
    private final LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType, int lastLecture, LocalDate lastActivityDate) {
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.engagementType = engagementType;
        this.lastLecture = lastLecture;
        this.lastActivityDate = lastActivityDate;
    }

    public String getCourseCode() {
        return course.courseCode();
    }

    public int getEnrollmentYear() {
        return enrollmentDate.getYear();
    }

    public String getEngagementType() {
        return engagementType;
    }

    public int getLastLecture() {
        return lastLecture;
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    public Month getLastActivityMonth() {
        return lastActivityDate.getMonth();
    }

    public long getMonthsSinceActive() {
        long diff = Period.between(lastActivityDate, LocalDate.now()).toTotalMonths();
        return diff;
    }

    public double getPercentComplete() {
        return ((lastLecture * 100.0) / course.lectureCount());
    }

    boolean watchLecture(int lecture, LocalDate date) {
        if (lecture <= 0 || lecture > course.lectureCount()) return false;

        this.lastLecture = lecture;
        this.lastActivityDate = date;
        this.engagementType = "Lecture " + lastLecture;

        return true;
    }
    @Override
    public String toString() {
        return String.format("%s: %s %d %s [%d]",
                getCourseCode(), getLastActivityMonth(), getLastActivityYear(), getEngagementType(), getMonthsSinceActive());
    }
}
