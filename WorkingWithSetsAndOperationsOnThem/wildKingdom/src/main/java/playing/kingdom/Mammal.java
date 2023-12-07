package playing.kingdom;

abstract class Mammal extends Animal {
    protected Mammal(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        System.out.printf("%n%s%s%s%n", "Going with speed, very ", speed, " like a ....!");
    }
    public abstract void shed();
}
