package playing.mathquestions;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public final class ApplicationSubMenu extends ApplicationMenu {
    private final String messageForTheSubMenu;
    private final LinkedList<String> subMenuOptions;
    private final int numberOfSpacesInFront;

    private static String validateMessageForTheMenu(String messageForTheMenu, int numberOfSpaces) throws InterruptedException {
        String errorMessage = String.format("%s%s%n", " ".repeat(numberOfSpaces),"ERROR please use a valid message/sentence for the submenu!");
        String messageProcessed;

        if (messageForTheMenu.isBlank()) {
            System.out.printf("%s", errorMessage);
            TimeUnit.SECONDS.sleep(1);
            messageProcessed = "None";
        } else {
            messageProcessed = messageForTheMenu.trim().toLowerCase();
        }

        return messageProcessed;
    }

    private static int validateNumberOfSpacesInFront(int numberOfSpacesInFront) throws InterruptedException {
        String errorMessage;

        if (numberOfSpacesInFront < 0) {
            numberOfSpacesInFront = 2;
            errorMessage = String.format("\033[31m%s%s\033[0m%n", " ".repeat(numberOfSpacesInFront), "ERROR please use an integer greater or equal to zero!");
            System.out.printf("%s", errorMessage);
            TimeUnit.SECONDS.sleep(1);
        }

        return numberOfSpacesInFront;
    }

    public ApplicationSubMenu(String headerMessage, int numberOfEntriesInTheMenu, int numberOfSpacesForMainMenu,
                              String messageForTheSubMenu, int numberOfSpacesInFront) throws InterruptedException {
        super(headerMessage, numberOfEntriesInTheMenu, numberOfSpacesForMainMenu);

        this.messageForTheSubMenu = validateMessageForTheMenu(messageForTheSubMenu, numberOfSpacesInFront);
        this.subMenuOptions = new LinkedList<>(List.of("(back/quit)"));
        this.numberOfSpacesInFront = validateNumberOfSpacesInFront(numberOfSpacesInFront);
    }

    public boolean addSubMenuOption(String givenOptionToAdd, int numberOfSpaces) throws InterruptedException {
        boolean toBeReturned = true;

        String errorMessage = String.format("\033[31m%s%s\033[0m%n", " ".repeat(numberOfSpaces),"ERROR please use a valid option with at least a word or multiple options separated by a comma!");
        List<String> tempListWithOptions = new LinkedList<>(List.of(givenOptionToAdd.trim().toLowerCase().split(",")));
        List<String> itemsAlreadyInTheSubMenu = new LinkedList<>();

        if (givenOptionToAdd.isBlank()) {
            System.out.printf("%s", errorMessage);
            TimeUnit.SECONDS.sleep(1);
            toBeReturned = false;
        } else {
            for (int i = tempListWithOptions.size() - 1; i >= 0; i--) {
                String item = tempListWithOptions.get(i).trim().toLowerCase();
                if (!subMenuOptions.contains(item)) {
                    subMenuOptions.push(item);
                } else {
                    itemsAlreadyInTheSubMenu.add(item);
                }
            }
        }

        return toBeReturned;
    }

    public void prettyPrintOfMessageSubMenu() {
        String toBePrinted = String.join(", ", subMenuOptions.subList(0, subMenuOptions.size() - 1)) + " ";

        if (toBePrinted.isBlank()) {
            System.out.printf("%s%s %s%s", " ".repeat(numberOfSpacesInFront), messageForTheSubMenu, subMenuOptions.getLast(), ": ");
        } else {
            System.out.printf("%s%s %s%s%s", " ".repeat(numberOfSpacesInFront), messageForTheSubMenu, toBePrinted, subMenuOptions.getLast(), ": ");
        }
    }

    public void printHeaderMessage(int numberOfEmptyLinesAbove, boolean lineAbove, boolean lineBelow) {
        super.printPrettyHeaderMessage(getNumberOfSpacesForMainMenu(), numberOfEmptyLinesAbove, lineAbove, lineBelow);
    }

    public void printHeaderSubMenuMessage(int numberOfEmptyLinesAbove) {
        this.printHeaderMessage(numberOfEmptyLinesAbove, true, true);
    }

    public int getNumberOfSpacesInFront() {
        return numberOfSpacesInFront;
    }

    public Player changeCurrentPlayer(Player newPlayer) throws InterruptedException {
        Player oldValue = getCurrentPlayer();

        setCurrentPlayer(newPlayer);

        if (!getCurrentPlayer().equals(oldValue)) {
            System.out.printf("%n\033[32m%s%s%s%s%s\033[0m%n", " ".repeat(getNumberOfSpacesForMainMenu()), "player changed! current player: ",
                    getCurrentPlayer().getFirstName(), ", ", getCurrentPlayer().getLastName());
            TimeUnit.SECONDS.sleep(1);
            oldValue = newPlayer;
        } else {
            System.out.printf("%n\033[32m%s%s\033[0m%n", " ".repeat(getNumberOfSpacesForMainMenu()), "current player was not changed");
        }

        return oldValue;
    }
}
