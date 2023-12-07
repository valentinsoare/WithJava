package playing.burger;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.ArrayList;

public class Meal {
    private Burger burger;
    private Item drink;
    private Item side;

    public Meal() {
        burger = new Burger("McChicken", 4.89);
        drink = new Item("Coke", "drink", 2.3);
        side = new Item("fries", "side", 2);
    }

    private class Item {
        private String name;
        private String type;
        private double price;
        private static int PRICE_WITH_DISCOUNT = 0;

        public Item(String name, String type) {
            this(name, type, PRICE_WITH_DISCOUNT);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("%s, %s, %.2f", name, type, price);
        }
    }

    private class Burger extends Item {

        private List<Item> listWithToppings;

        public Burger(String name, double price) {
            super(StringUtils.capitalize(name), "burger", price);
            this.listWithToppings = new ArrayList<>();
        }

        public double getTotalPrice() {
            double totalPrice = 0;
            totalPrice += getPrice();

            for (Item element : listWithToppings) {
                totalPrice += element.price;
            }

            return totalPrice;
        }

        @Override
        public String toString() {
            StringBuilder toReturn = new StringBuilder();
            toReturn.append(String.format("%s%n%n", super.toString()));

            for (Item listWithTopping : listWithToppings) {
                toReturn.append(String.format("%24s", listWithTopping)).append("\n");
            }

            toReturn.append(String.format("%s", "-".repeat(25))).append("\n").
                    append(String.format("%s%.2f", "TOPPINGS TOTAL PRICE ", getTotalPrice() - getPrice())).append("\n");

            return toReturn.toString();
        }

        public double getPrice() {
            return super.price;
        }
    }

    public boolean addTopping(String... toppings) {
        List<Item> toppingsToBeAdded = new ArrayList<>();
        List<String> element;
        boolean valueToReturn = false;

        for (String topping : toppings) {

            element = new ArrayList<>(List.of(StringUtils.capitalize(topping).split(",")));

            if (element.size() == 2) {
                toppingsToBeAdded.add(new Item(element.get(0), "topping", Double.parseDouble(element.get(1))));
            } else if (element.size() == 1) {
                toppingsToBeAdded.add(new Item(element.get(0), "topping"));
            }
        }

        if (!toppingsToBeAdded.isEmpty()) {
            burger.listWithToppings.addAll(toppingsToBeAdded);
            valueToReturn = true;
        }

        return valueToReturn;
    }

    public Double getTotal() {
        return burger.getTotalPrice() + side.price + drink.price;
    }

    @Override
    public String toString() {

        return String.format("%n%s%n%s%n%s%n%s%n%s%.2f", burger, side, drink, "-".repeat(18), "TOTAL PRICE: ", getTotal());
    }
}
