package playing.store;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tv extends ProductForSale {
    private String resolution;
    private String manufacturer;

    private static String validateManufacturer(String manufacturer) {
        String valueToBeReturn = "None";
        LinkedList<String> approvedManufacturers = new LinkedList<>(Arrays.asList("samsung", "lg", "philips", "sony"));
        String errorMessage = String.format("%n%s%s%n", "ERROR please use a manufacturer from the list: ", approvedManufacturers);

        if (manufacturer.isBlank()) {
            System.out.printf("%s%s", " ".repeat(5), errorMessage);
        } else {
            String stringToBeProcessed = manufacturer.toLowerCase().trim();

            if (!approvedManufacturers.contains(stringToBeProcessed)) {
                System.out.printf("%s%s", " ".repeat(5), errorMessage);
            } else {
                valueToBeReturn = stringToBeProcessed.toUpperCase();
            }
        }

        return valueToBeReturn;
    }

    private static String validateResolutions(String resolution) {
        String valueToReturn = "None";
        LinkedList<String> approvedResolutions = new LinkedList<>(Arrays.asList("4k ultra hd",
                "full hd",
                "hd Ready",
                "standard definition"));
        String errorMessage = String.format("%n%s%s%n", "ERROR please use a valid resolution from the list: ", approvedResolutions);

        if (resolution.isBlank()) {
            System.out.printf("%s%s", " ".repeat(5), errorMessage);
        } else {
            String toBeProcessed = resolution.toLowerCase().trim();

            if (!approvedResolutions.contains(toBeProcessed)) {
                System.out.printf("%s%s", " ".repeat(5), errorMessage);
            } else {
                toBeProcessed = toBeProcessed.replace("hd", "HD");

                int i = 0;
                int location = 0;
                ArrayList<Integer> locationSpaces = new ArrayList<>();

                while (i < toBeProcessed.length()) {
                    location = toBeProcessed.indexOf(" ", location + 1);

                    if (!locationSpaces.contains(location) && (location >= 0)) {
                        locationSpaces.add(location);
                    }

                    i++;
                }

                StringBuilder newString = new StringBuilder();
                newString.append(Character.toTitleCase(toBeProcessed.charAt(0)));

                for (int j = 1; j < toBeProcessed.length(); j++) {
                    if (locationSpaces.contains(j)) {
                        newString.append(" ");
                        newString.append(Character.toTitleCase(toBeProcessed.charAt(j + 1)));
                    } else {
                        newString.append(toBeProcessed.charAt(j));
                    }
                }
                valueToReturn = String.valueOf(newString);
            }
        }

        return valueToReturn;
    }

    protected Tv(Double price, String description, String manufacturer, String resolution) {
        super("tv", price, description);

        this.manufacturer = validateManufacturer(manufacturer);
        this.resolution = validateResolutions(resolution);
    }


    public String getResolution() {
        return resolution;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setResolution(String resolution) {
        this.resolution = validateResolutions(resolution);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = validateManufacturer(manufacturer);
    }

    @Override
    public String toString() {
        return super.toString() + ", Tv{" +
                "resolution='" + resolution + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
