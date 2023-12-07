package playing.mappingLocations;

import java.util.Arrays;

public class Park extends Point implements Mappable {
    private String name;

    public Park(String name, String location, int emptySpaces) throws InterruptedException {
        super(location, emptySpaces);
        this.name = setName(name, emptySpaces);
    }

    public static String setName(String name, int emptySpaces) {
        String word, returningASstring = "";
        StringBuilder valueToReturn = new StringBuilder();

        if (name.isBlank()) {
            System.out.printf("%n%s%s", " ".repeat(emptySpaces), "ERROR no valid name was given! It will default to 'Unknown'");
            valueToReturn.append("Unknown");
        } else {
            for (String element : Arrays.asList(name.trim().toLowerCase().split(" +"))) {
                word = element.trim();
                valueToReturn.append(word.toUpperCase().charAt(0) + word.substring(1)).append(" ");
            }

            returningASstring = valueToReturn.toString().trim();
        }

        return returningASstring;
    }

    public String getName() {
        return name;
    }

    @Override
    public void render() {
        System.out.printf("%n%s%s%s%s%s", " ".repeat(getEmptySpaces()), getName(), " as ", super.getClass().getSimpleName().toUpperCase(), " (" + getCoordinates() + ") ");
    }
}
