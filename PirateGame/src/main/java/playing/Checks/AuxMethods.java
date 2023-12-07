package playing.Checks;

public class AuxMethods {
    public static String processFirstNameLastName(String name) throws InterruptedException {
        String defaultReturn = "None";
        StringBuilder toReturn = new StringBuilder();

        if (name == null || name.isBlank()) {
            System.out.printf("%n%s", "ERROR please use a value that is not empty or null");
            Thread.sleep(1000);
            return defaultReturn;
        }

        String[] parsedName = name.trim().split(" +");

        if (parsedName.length < 2) {
            System.out.printf("%n%s", "ERROR please use a lastname and firstname separated by a comma!");
            return defaultReturn;
        }

        for (int i = 0; i < parsedName.length; i++) {
            String word = parsedName[i].trim();
            toReturn.append(" ").append(word.toUpperCase().charAt(0)).append(word.substring(1));

            if (i == parsedName.length - 2) {
                toReturn.append(",");
            }
        }

        return toReturn.toString().stripLeading();
    }

    public static String processGameName(String gameName) throws InterruptedException {
        String defaultReturn = "None";
        StringBuilder toReturn = new StringBuilder();

        if (gameName == null || gameName.isBlank()) {
            System.out.printf("%n%s", "ERROR please use a valid game name. Can contain only alnum chars.");
            Thread.sleep(1000);
            return defaultReturn;
        }

        String[] nameOfGame = gameName.split(" +");

        for (int i = 0; i < nameOfGame.length; i++) {
            String word = nameOfGame[i].trim();
            toReturn.append(word.toUpperCase().charAt(0)).append(word.substring(1));

            if (i != nameOfGame.length - 1) toReturn.append(" ");
        }

        return toReturn.toString();
    }
}
