package WithJava.Burger.src.main.java.playing.burger;

public class App {
    public static void main(String[] args) {
        Meal lastMeal = new Meal();
        lastMeal.addTopping("onions, 1.4", "bacon, 2.5", "pickles, 2.2", "condiments, 1.9");

        System.out.println(lastMeal);
    }
}
