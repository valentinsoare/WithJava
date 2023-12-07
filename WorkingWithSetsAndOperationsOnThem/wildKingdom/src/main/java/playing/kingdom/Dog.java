package playing.kingdom;

public class Dog extends Animal {

    protected Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        System.out.printf("%n%s%s%s%n", "Going very ", speed, " to the destination!");
    }

    @Override
    public void makeNoise() {
        System.out.printf("%n%s%s%n", getExplicitType(), " is making noise!");
    }

    @Override
    public void attack() {
        System.out.printf("%n%s%s%n", getExplicitType(), " mrrrr......");
    }

    @Override
    public void eat() {
        System.out.printf("%n%s%s%s%n", getExplicitType(), " eating the read berries and get more weight++ ", getWeight());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
