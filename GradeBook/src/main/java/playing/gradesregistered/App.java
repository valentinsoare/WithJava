package playing.gradesregistered;

public class App {
    public static void main( String[] args ) throws InterruptedException {
        GradeBook computerScienceMath = new GradeBook("Computer Science Math");
        computerScienceMath.askForGrades();
        computerScienceMath.askForGrades();
        computerScienceMath.calculateStatsOnGrades();
    }
}
