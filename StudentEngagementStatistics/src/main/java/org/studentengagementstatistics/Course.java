package WithJava.StudentEngagementStatistics.src.main.java.org.studentengagementstatistics;

public record Course(String courseCode, String title, int lectureCount) {
    @Override
    public String toString() {
        return String.format("%s < %s >", courseCode, title);
    }
}
