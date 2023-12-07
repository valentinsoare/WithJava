package WithJava.PlayingASong.src.main.java.playing.playingasong;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class AuxMethodsAndChecks {
    public static String checkNameProperly(String inputName, int emptySpaces) throws InterruptedException {
        StringBuilder toReturnAsString = new StringBuilder();
        String eToBeAdded = "";
        String errorMessage = String.format("%n%s%s%n", " ".repeat(emptySpaces), "ERROR please use a proper name with alpha chars!");

        if (inputName.isBlank()) {
            toReturnAsString.append("Unknown Song");
            System.out.printf("%s", errorMessage);
            Thread.sleep(1000);
        } else {
            for (String element : new ArrayList<>(Arrays.asList(inputName.split(" +")))) {
                eToBeAdded = element.trim();
                toReturnAsString.append(eToBeAdded.toUpperCase().charAt(0) + eToBeAdded.substring(1)).append(" ");
            }

            eToBeAdded = toReturnAsString.toString().trim();
        }

        return eToBeAdded;
    }

    public static String checkFirstNameLastName(String inputName, int emptySpaces) throws InterruptedException {
        StringBuilder valueToReturn = new StringBuilder();
        String tempE = "";

        if (inputName.isBlank()) {
            System.out.printf("%n%s%s%n", " ".repeat(emptySpaces), "ERROR please use a valid firstname and lastname separated by a comma!");
            valueToReturn.append("Unknown One");
            Thread.sleep(1000);
        } else {
            List<String> temp = new ArrayList<>(Arrays.asList(inputName.split(" ")));

            for (String s : temp) {
                tempE = s.trim();
                valueToReturn.append(tempE.toUpperCase().charAt(0) + tempE.substring(1)).append(" ");
            }
        }

        return valueToReturn.toString().trim();
    }

    public static int checkEmptySpaces(int howManySpaces) {
        int toReturn = howManySpaces;

        if (toReturn < 0) {
            toReturn = 2;
        }

        return toReturn;
    }
}
