package playing.kingdom;

import java.util.List;
import java.util.ArrayList;

public abstract class Animal {
    protected String type;
    private String size;
    private double weight;

    private static String validateSize(String size) {
        String toReturnResult = "";
        ArrayList<String> animalSizes = new ArrayList<>(List.of("small", "medium", "large"));

        if (size.isBlank()) {
            System.err.printf("%n%s%n", "ERROR - please use a valid size, Ex: ' small, medium or large'!");
            toReturnResult = "None";
        } else {
            if (!animalSizes.contains(size.trim().toLowerCase())) {
                System.err.printf("%n%s%n", "ERROR - please use a valid size, Ex: ' small, medium or large'!");
                toReturnResult = "None";
            } else {
                char[] tmpArr = size.toCharArray();
                tmpArr[0] = Character.toTitleCase(tmpArr[0]);
                toReturnResult = String.valueOf(tmpArr);
            }
        }

        return toReturnResult;
    }

    private static double validateWeight(double weight) {
        double valueToReturn = -1.0;

        if (weight <= 0) {
            System.err.printf("%n%s%n", "ERROR - please use a valid weight greater than zero!");
        } else {
            valueToReturn = weight;
        }

        return valueToReturn;
    }

    private static String validateType(String type) {
        String valueToReturn = "";

        ArrayList<String> typeOfAnimalsAccepted = new ArrayList<>(List.of("wolf", "dog", "bear", "tiger"));
        String formatted = type.trim().toLowerCase();

        if ((type.isBlank()) || (!typeOfAnimalsAccepted.contains(formatted)))  {
            System.err.printf("%n%s%n", "ERROR - please use a valid type, Ex: wolf, dog, bear or tiger!");
            valueToReturn = "None";
        } else {
            char[] typeChr = formatted.toCharArray();
            typeChr[0] = Character.toTitleCase(typeChr[0]);
            valueToReturn = String.valueOf(typeChr);
        }

        return valueToReturn;
    }

    protected Animal(String type, String size, double weight) {
        this.type = validateType(type);
        this.size = validateSize(size);
        this.weight = validateWeight(weight);
    }

    public abstract void move(String speed);
    public abstract void makeNoise();
    public abstract void attack();
    public abstract void eat();

    public String getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public final String getExplicitType() {
        return getClass().getSimpleName() + ", " + type;
    }

    @Override
    public String toString() {
        return "Animal "+ "type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", weight=" + weight;
    }
}
