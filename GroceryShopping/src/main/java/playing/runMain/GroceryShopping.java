package WithJava.GroceryShopping.src.main.java.playing.runMain;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GroceryShopping {
    private static final Scanner scanner = new Scanner(System.in);
    private static String valueFromUser = "";

    private static int printingHeader(String headerMessage) {
        int upperBound = 1;
        int lowerBound = 1;
        int letterToWorkOn;
        Random rnd = new Random();
        ArrayList<String> headerWithMessageSplit = new ArrayList<>(List.of(headerMessage.trim().split(" ")));
        StringBuilder messageProcessed = new StringBuilder("_ ");

        for (String s : headerWithMessageSplit) {
            for (int j = 0; j < s.length(); j += 1) {
                upperBound = (s.length() - 1);
                letterToWorkOn = rnd.nextInt(lowerBound, upperBound);
                if ((j == 0) || (letterToWorkOn == j)) {
                    messageProcessed.append((String.valueOf(s.charAt(j))).toUpperCase());
                } else {
                    messageProcessed.append(s.charAt(j));
                }
            }
            messageProcessed.append(" ");
        }

        messageProcessed.append("_");
        int lineLengthAboveAndBellowMessageHeader = (messageProcessed.length() * 2);
        String messageWithSpaces = String.format("%" + (messageProcessed.length() / 2) +"s%s%n", " ",messageProcessed);

        System.out.printf("%n%" + (messageProcessed.length() / 2) + "s%s%n", " ","-".repeat(lineLengthAboveAndBellowMessageHeader));
        System.out.printf("%" + (messageProcessed.length() / 2) + "s%s", " ",messageWithSpaces);
        System.out.printf("%" + (messageProcessed.length() / 2) + "s%s%n", " ","-".repeat(lineLengthAboveAndBellowMessageHeader));

        return messageProcessed.length();
    }

    private static void printMainMenu(int numberOfSpacesFromLeft) {
        ArrayList<String> optionsToPrint = new ArrayList<>(Arrays.asList("0 - to shutdown",
                "1 - to add item(s) to shopping list (comma delimited list)",
                "2 - to remove any item(s) from shopping list (comma delimited list)",
                "3 - print the shopping list"));

        System.out.printf("%s%s%n%n", " ".repeat(numberOfSpacesFromLeft / 2),"Available actions: ");

        for (String s : optionsToPrint) {
            System.out.printf("%s%s%n", " ".repeat(numberOfSpacesFromLeft / 2), s);
        }

        System.out.printf("%n%s%s", " ".repeat(numberOfSpacesFromLeft / 2),"Enter a number for which action you want to do: ");
    }

    private static void useInputAndActOnIt(int optionFromUser, int spacesToUse, List<String> groceries) throws InterruptedException {
        switch (optionFromUser) {
            case 0 -> {
                System.out.printf("%n%" + (spacesToUse / 2) + "s%s%n%n", " ","Exiting...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("\033[H\033[2J");
                System.out.flush();
                System.exit(0);
            }
            case 1 -> printAddMenuItem(spacesToUse, groceries);
            case 2 -> printRemoveMenuItem(spacesToUse, groceries);
            case 3 -> printShoppingListInSortedOrder(spacesToUse, groceries);
        }
    }

    public static void printShoppingListInSortedOrder(int spaces, List<String> groceries) throws InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();

        printingHeader("grocery shopping list");

        System.out.printf("%s%s%n%n", " ".repeat(spaces / 2), "Available actions: ");
        System.out.printf("%s%s", " ".repeat(spaces / 2), "Items in sorted order from the list: ");

        if (groceries.isEmpty()) {
            System.out.printf("%s", "None");
        } else {
            System.out.printf("%s", String.join(", ", groceries));
        }

        TimeUnit.SECONDS.sleep(2);
    }

    private static void workOnInputAsAListToRemove(List<String> groceries, List<String> tmp, int spaces) {
        ArrayList<String> itemsThatAreNotInTheListToRemove = new ArrayList<>();

        for (String s : tmp) {
            if (!groceries.contains(s)) {
                itemsThatAreNotInTheListToRemove.add(s);
            } else {
                groceries.remove(s);
            }
        }

        tmp.removeAll(itemsThatAreNotInTheListToRemove);

        int lengthOfItemsNotInList = itemsThatAreNotInTheListToRemove.size();
        if (lengthOfItemsNotInList != 0) {
            System.err.printf("%n%s%s%s%s%n%s%s%s%s%n", " ".repeat(spaces / 2) ,"INFO - from what you gave us ", lengthOfItemsNotInList, " items are not in the list!",
                    " ".repeat(spaces / 2), "Rest of ", tmp.size(), " items were removed from groceries list.");
            System.err.printf("%s%s%s", " ".repeat(spaces / 2), "Items that were not in the list: ", itemsThatAreNotInTheListToRemove);
        }

        System.out.printf("%n%s%s%s%n", " ".repeat(spaces / 2), "Item(s) removed from the list: ", tmp);
    }

    private static void printRemoveMenuItem(int spaces, List<String> groceries) throws InterruptedException {
        ArrayList<String> tmp;

        while (true) {
            tmp = new ArrayList<>();
            System.out.println("\033[H\033[2J");
            System.out.flush();

            printingHeader("grocery shopping list");

            System.out.printf("%s%s%n%n", " ".repeat(spaces / 2), "Available actions: ");
            System.out.printf("%s%s", " ".repeat(spaces / 2),"Items in the list: ");

            if (groceries.isEmpty()) {
                System.out.printf("%s", "None");
            } else {
                System.out.printf("%s", String.join(",", groceries));
            }

            System.out.printf("%n%s%s", " ".repeat(spaces / 2), "Provide the item(s) to remove them (type 'back' to return): ");
            valueFromUser = scanner.nextLine();

            if (valueFromUser.isBlank()) {
                System.err.printf("%n%s%s%n", " ".repeat(spaces / 2), "ERROR please use a valid grocery item to remove!");
            } else if (valueFromUser.equalsIgnoreCase("back")) {
                System.out.printf("%n%s%s%n", " ".repeat(spaces / 2),"Going back to main menu....");
                TimeUnit.SECONDS.sleep(1);
                return;
            } else {
                ArrayList<String> itemsToBeProcessed = new ArrayList<>((List.of(valueFromUser.toLowerCase().split(","))));

                for (String s : itemsToBeProcessed) {
                    tmp.add(s.trim());
                }
                workOnInputAsAListToRemove(groceries, tmp, spaces);
            }
            TimeUnit.SECONDS.sleep(3);
        }
    }

    private static void workOnInputAsAListToAdd(List<String> groceries, List<String> tmp, int spaces) {
        ArrayList <String> itemsThatAlreadyExists = new ArrayList<>();

        for (String s : tmp) {
            if (groceries.contains(s)) {
                itemsThatAlreadyExists.add(s);
            } else {
                groceries.add(s);
            }
        }
        tmp.removeAll(itemsThatAlreadyExists);

        int alreadyExists = itemsThatAlreadyExists.size();
        if (alreadyExists != 0) {
            System.err.printf("%n%s%s%s%s%n%s%s%s%s%n", " ".repeat(spaces / 2) ,"INFO - from what you gave us ", alreadyExists, " items already are in the list!",
                    " ".repeat(spaces / 2), "Rest of ", tmp.size(), " items were added to groceries list.");
            System.err.printf("%s%s%s", " ".repeat(spaces / 2), "Items that already exists: ", itemsThatAlreadyExists);
        }

        groceries.sort(Comparator.naturalOrder());
        System.out.printf("%n%s%s%s%n", " ".repeat(spaces / 2), "Item(s) added to the list: ", tmp);
    }

    private static void printAddMenuItem(int spaces, List<String> groceries) throws InterruptedException {
        ArrayList<String> tmp;

        while (true) {
            tmp = new ArrayList<>();
            System.out.println("\033[H\033[2J");
            System.out.flush();

            printingHeader("grocery shopping list");

            System.out.printf("%s%s%n%n", " ".repeat(spaces / 2), "Available actions: ");
            System.out.printf("%s%s", " ".repeat(spaces / 2),"Items in the list: ");

            if (groceries.isEmpty()) {
                System.out.printf("%s", "No items added");
            } else {
                System.out.printf("%s", String.join(",", groceries));
            }

            System.out.printf("%n%s%s", " ".repeat(spaces / 2), "Provide the item(s) to add them (type 'back' to return): ");
            valueFromUser = scanner.nextLine();

            if (valueFromUser.isBlank()) {
                System.err.printf("%n%s%s%n", " ".repeat(spaces / 2), "ERROR please use a valid input!");
            } else if (valueFromUser.equalsIgnoreCase("back")) {
                System.out.printf("%n%s%s%n", " ".repeat(spaces / 2),"Going back to main menu....");
                TimeUnit.SECONDS.sleep(1);
                return;
            } else {
                ArrayList<String> toUseLocal = new ArrayList<>((List.of(valueFromUser.toLowerCase().split(","))));

                for (String s : toUseLocal) {
                    tmp.add(s.trim());
                }
                workOnInputAsAListToAdd(groceries, tmp, spaces);
            }
            TimeUnit.SECONDS.sleep(3);
        }
    }

    public static void runMenu() throws InterruptedException {
        int optionAsInteger = -1;
        int numberOfSpaces;
        Integer[] options = {0, 1, 2, 3};
        String errorMessage;
        ArrayList<String> groceries = new ArrayList<>();

        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            numberOfSpaces = printingHeader("grocery shopping list");
            errorMessage = String.format("%n%s%s%n"," ".repeat(numberOfSpaces / 2) ,"ERROR please use one of those three options, 0 - to shutdown, 1 - to add item, 2 - to remove item, 3 - to print the list!");
            printMainMenu(numberOfSpaces);
            valueFromUser = scanner.nextLine();

            try {
                optionAsInteger = Integer.parseInt(valueFromUser);

                if (!Arrays.asList(options).contains(optionAsInteger)) {
                    System.err.printf("%s", errorMessage);
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    useInputAndActOnIt(optionAsInteger, numberOfSpaces, groceries);
                }
            } catch (NumberFormatException e) {
                System.err.printf("%s",errorMessage);
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
