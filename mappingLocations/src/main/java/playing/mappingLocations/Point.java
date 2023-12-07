package WithJava.mappingLocations.src.main.java.playing.mappingLocations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Point {
    private String location;
    private Double latitude;
    private Double longitude;
    private int emptySpaces;
    private List<Double> latitudeAndLongitude = new ArrayList<>();

    protected Point(String location, int emptySpaces) throws InterruptedException {
        this.emptySpaces = setEmptySpaces(emptySpaces, true);
        this.latitudeAndLongitude = setLocationLatitudeLongitude(location, true);
    }

    public int setEmptySpaces(int emptySpaces, boolean forConstructor) {
        int defaultValue = emptySpaces;

        if (emptySpaces <= 0) {
            defaultValue = 2;
        }

        if (!forConstructor) {
            this.emptySpaces = defaultValue;
        }

        return defaultValue;
    }

    public String getLocation() {
        return location;
    }

    public List<Double> setLocationLatitudeLongitude(String location, boolean forConstructor) throws InterruptedException {
        String errorMessage = String.format("%n%s%s", " ".repeat(emptySpaces), "ERROR please use a valid location with latitude and longitude separated by a comma.");

        if (location == null || location.isBlank()) {
            location = "0.0, 0.0";
            latitudeAndLongitude = new ArrayList<>(Arrays.asList(0.0, 0.0));
            System.out.printf("%s", errorMessage);
            Thread.sleep(1000);
        } else {
            for (String element : location.trim().split(",")) {
                try {
                    latitudeAndLongitude.add(Double.parseDouble(element.trim()));
                } catch (NumberFormatException e) {
                    System.out.printf("%s", errorMessage);
                    Thread.sleep(1000);
                    latitudeAndLongitude.add(0.0);
                }
            }
        }

        if (!forConstructor) {
            this.latitude = latitudeAndLongitude.get(0);
            this.longitude = latitudeAndLongitude.get(1);
        }

        this.location = location;

        return latitudeAndLongitude;
    }

    public List<Double> getCoordinates() {
        return latitudeAndLongitude;
    }

    public int getEmptySpaces() {
        return emptySpaces;
    }
}
