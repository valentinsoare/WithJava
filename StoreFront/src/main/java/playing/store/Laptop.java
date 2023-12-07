package WithJava.StoreFront.src.main.java.playing.store;

import java.util.List;
import java.util.ArrayList;

public class Laptop extends ProductForSale {
    private String manufacturer;
    private String processorType;
    private Integer numberOfCores;
    private Integer screenSize;

    private static Integer validateQuantity(Integer quantity) {
        String errorMessage = String.format("%n%s%n", "ERROR - please use a valid quantity greater than zero an in integer format!");
        Integer valueToReturn = -1;

        if (quantity <= 0) {
            System.out.printf("%s%s", " ".repeat(5), errorMessage);
        } else {
            valueToReturn = quantity;
        }

        return valueToReturn;
    }


    private static String validateManufacturer(String manufacturer) {
        String valueToReturn = "None";
        String manufacturerToBeProcessed = manufacturer.toLowerCase().trim();
        ArrayList<String> approvedManufacturers = new ArrayList<>(List.of("asus","lenovo", "dell", "hp", "apple"));
        String errorMessage = String.format("%n%s%s%n", "ERROR please use a valid manufacturer from the list: ",
                String.join(", ", approvedManufacturers));

        if (manufacturer.isBlank() || (!approvedManufacturers.contains(manufacturerToBeProcessed))) {
            System.out.printf("%s%s", " ".repeat(5), errorMessage);
        } else {
             valueToReturn = manufacturerToBeProcessed.toUpperCase();
        }

        return valueToReturn;
    }

    private static String validateProcessorType(String processorType) {
        String valueToReturn = "None";
        String processedString = processorType.toLowerCase().trim();
        ArrayList<String> approvedProcessorType = new ArrayList<>(List.of("intel", "amd"));
        String errorMessage = String.format("%n%s%n", "ERROR please use a valid processor type, intel or amd!");

        if ((processorType.isBlank()) || (!approvedProcessorType.contains(processedString))) {
            System.out.printf(errorMessage);
        }

        return valueToReturn;
    }

    private static Integer validateNumberOfCores(Integer numberOfCores) {
        Integer valueToReturn = -1;
        String errorMessage = String.format("%n%s%n", "ERROR please use a number of cores greater than zero and an integer!");

        if (numberOfCores <= 0) {
            System.out.printf("%n%s%s%n", " ".repeat(5), errorMessage);
        } else {
            valueToReturn = numberOfCores;
        }

        return valueToReturn;
    }

    private static Integer validateScreenSize(Integer screenSize) {
        Integer valueToReturn = -1;
        ArrayList<Integer> approvedScreenSizes = new ArrayList<>(List.of(14, 15, 16, 17, 18));
        String errorMessage = String.format("%n%s%s%n", "ERROR please use a valid screen size from the list: ", String.join(", ", approvedScreenSizes.toString()));

        if (!approvedScreenSizes.contains(screenSize)) {
            System.out.printf(errorMessage);
        } else {
            valueToReturn = screenSize;
        }

        return valueToReturn;
    }

    public Laptop(Double price, String description, String manufacturer, String processorType, int numberOfCores, int screenSize) {
        super("laptop", price, description);

        this.manufacturer = validateManufacturer(manufacturer);
        this.processorType = validateProcessorType(processorType);
        this.numberOfCores = validateNumberOfCores(numberOfCores);
        this.screenSize = validateScreenSize(screenSize);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getProcessorType() {
        return processorType;
    }

    public Integer getNumberOfCores() {
        return numberOfCores;
    }

    public Integer getScreenSize() {
        return screenSize;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = validateManufacturer(manufacturer);
    }

    public void setProcessorType(String processorType) {
        this.processorType = validateProcessorType(processorType);
    }

    public void setNumberOfCores(Integer numberOfCores) {
        this.numberOfCores = validateNumberOfCores(numberOfCores);
    }

    public void setScreenSize(Integer screenSize) {
        this.screenSize = validateScreenSize(screenSize);
    }

    @Override
    public String toString() {
        return super.toString() + " Laptop{" +
                "manufacturer='" + manufacturer + '\'' +
                ", processorType='" + processorType + '\'' +
                ", numberOfCores=" + numberOfCores +
                ", screenSize=" + screenSize +
                '}';
    }
}
