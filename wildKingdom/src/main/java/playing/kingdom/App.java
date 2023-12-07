package playing.kingdom;

public class App {
    public static void main( String[] args ) {
        Dog bobita = new Dog("wolf", "small", 12);

        System.out.println(bobita);

        doingStuff(bobita);

        System.out.println(bobita.getExplicitType());

        bobita.move("fast");
    }

    private static void doingStuff(Animal myAnimal) {
        myAnimal.move("fast");
        myAnimal.makeNoise();
    }
}
