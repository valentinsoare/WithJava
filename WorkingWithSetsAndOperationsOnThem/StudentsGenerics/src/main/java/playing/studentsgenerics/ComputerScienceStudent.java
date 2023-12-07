package playing.studentsgenerics;

public class ComputerScienceStudent extends Student {
    private Double percentComplete;

    public ComputerScienceStudent() {
        this.percentComplete = rnd.nextDouble() * 100;
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public String toString() {
        return String.format("%-28s%.1f%%", super.toString(), percentComplete);
    }
}
