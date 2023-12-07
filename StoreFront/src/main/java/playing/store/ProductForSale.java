package WithJava.StoreFront.src.main.java.playing.store;

import java.util.*;

public abstract class ProductForSale {
    private String type;
    private Double price;
    private String description;

    private static String validateType(String type) {
        boolean toContinue = true;
        String valueToReturn = "None";
        String errorMessage = String.format("%n%s", "ERROR please use a valid type containing only alpha characters!");
        String toBeChecked = type.trim().toLowerCase();
        ArrayList<String> typeOfProductsForSale = new ArrayList<>(Arrays.asList("laptop", "tv", "desktop", "s22", "iphone"));

        if (toBeChecked.isBlank()) {
            System.out.printf("%n%s%s%n", " ".repeat(5), errorMessage);
        } else {
            for (int i = 0; i < toBeChecked.length(); i++) {
                if (!Character.isLetterOrDigit(toBeChecked.charAt(i))) {
                    System.out.printf("%n%s%s%n", " ".repeat(5), errorMessage);
                    toContinue = false;
                }
            }

            if (toContinue) {
                if (!typeOfProductsForSale.contains(toBeChecked)) {
                    System.out.printf("%n%s%s%n", " ".repeat(5), errorMessage);
                } else {
                    valueToReturn = toBeChecked;
                }
            }
        }

        return valueToReturn;
    }

    private static Double validatePrice(Double price) {
        double valueToReturn = 0;
        String errorMessage = String.format("%n%s%s%n", " ".repeat(5),"ERROR please use a valid price, greater than zero!");

        if (price <= 0.0) {
            System.out.printf("%n%s", errorMessage);
        } else {
            valueToReturn = price;
        }

        return valueToReturn;
    }

    private static String validateDescription(String description) {
        String valueToReturn = "None";
        String errorMessage = String.format("%n%s%s%n", " ".repeat(5),"ERROR please use a valid description. Cannot start with a digit!");
        char[] letters = description.trim().toCharArray();

        StringBuilder descriptionToBeReturned = new StringBuilder();

        if (Character.isDigit(letters[0])) {
            System.out.printf("%s", errorMessage);
        } else {
            letters[0] = Character.toTitleCase(letters[0]);
            LinkedList<String> listToBeParsed = new LinkedList<>(List.of(String.valueOf(letters).split(" ")));

            for (String s : listToBeParsed) {
                descriptionToBeReturned.append(s.trim());
            }

            valueToReturn = descriptionToBeReturned.toString();
        }

        return valueToReturn;
    }

    protected ProductForSale(String type, Double price, String description) {
        this.type = validateType(type);
        this.price = validatePrice(price);
        this.description = validateDescription(description);
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = validateType(type);
    }

    public void setPrice(Double price) {
        this.price = validatePrice(price);
    }

    public void setDescription(String description) {
        this.description = validateDescription(description);
    }

    public double getSalesPrice(int quantity) {
        return quantity * getPrice();
    }

    @Override
    public String toString() {
        return "ProductForSale{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
