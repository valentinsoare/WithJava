package WithJava.mappingLocations.src.main.java.playing.mappingLocations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Line {
    private int emptySpaces;
    private String locations;
    private List<List<Double>> latitudesAndLongitudes;

    protected Line(int emptySpaces, String locations) throws InterruptedException {
        this.emptySpaces = setEmptySpaces(emptySpaces, true);
        this.latitudesAndLongitudes = setLocationsAndLatitudesAndLongitudes(locations, true);
    }

    public int setEmptySpaces(int emptySpaces, boolean forConstructor) {
        int valueToReturn = emptySpaces;

        if (emptySpaces <= 0) {
            valueToReturn = 2;
        }

        if (!forConstructor) {
            this.emptySpaces = emptySpaces;
        }

        return valueToReturn;
    }

    public List<List<Double>> setLocationsAndLatitudesAndLongitudes(String locations, boolean forConstructor) throws InterruptedException {
        String errorMessage = String.format("%n%s%s%n", " ".repeat(emptySpaces), "ERROR please use at least 2 latitude and longitude groups separated by point and comma.");
        List<String> temp = new ArrayList<>(Arrays.asList(locations.trim().split(";")));
        List<List<Double>> toReturn = new ArrayList<>();
        List<Double> toBeAdded;

        if (temp.size() < 2) {
            System.out.printf("%s", errorMessage);
            toReturn.addAll(Arrays.asList(new ArrayList<>(Arrays.asList(0.0, 0.0)), new ArrayList<>(Arrays.asList(0.0, 0.0))));
            Thread.sleep(1000);
        } else {
            for (String s : temp) {
                toBeAdded = new ArrayList<>();
                for (String element : s.trim().split(",")) {
                    try {
                        toBeAdded.add(Double.parseDouble(element));
                    } catch (NumberFormatException e) {
                        System.out.printf("%s", errorMessage);
                        toBeAdded.add(0.0);
                        Thread.sleep(1000);
                    }
                }

                toReturn.add(toBeAdded);
            }
        }

        if (!forConstructor) {
            this.latitudesAndLongitudes = toReturn;
        }

        this.locations = locations;

        return toReturn;
    }

    public List<List<Double>> getCoordinates() {
        return latitudesAndLongitudes;
    }

    public int getEmptySpaces() {
        return emptySpaces;
    }
}
