
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

public class PlacesToGo {
    private static Place currentPlace;
    private static int currentIndex;
    private static int lengthOfMessage;
    private static final Scanner input = new Scanner(System.in);
    private static String valueFromUser = "";
    private static final LinkedList<Place> placesToVisit = new LinkedList<>();
    private static ListIterator<Place> iteratorForPlaces;

    private static void printHeader(String givenMessage) {
        int upperBound;
        int lowerBound = 1;
        Random rnd = new Random();

        StringBuilder processedMessage = new StringBuilder();
        ArrayList<String> listWithMessage = new ArrayList<>(List.of(givenMessage.split(" ")));

        for (int i = 0; i < listWithMessage.size(); i += 1) {
            String word = listWithMessage.get(i);
            upperBound = word.length();

            for (int j = 0; j < word.length(); j += 1) {
                if (j == 0) {
                    processedMessage.append(word.charAt(j));
                } else if (j != rnd.nextInt(lowerBound, upperBound)) {
                    processedMessage.append(String.valueOf(word.charAt(j)).toUpperCase());
                } else {
                    processedMessage.append(word.charAt(j));
                }
            }

            if (i < listWithMessage.size() - 1) {
                processedMessage.append(" _ ");
            }
        }

        lengthOfMessage = processedMessage.length();
        String lineToBePrintedBellowHeader = "-".repeat(lengthOfMessage * 2);

        System.out.printf("%n%s%s%n", " ".repeat(lengthOfMessage - 1), processedMessage);
        System.out.printf("%s%s", " ".repeat(lengthOfMessage / 2) ,lineToBePrintedBellowHeader);
    }

    private static void printMenu() {
        LinkedList<String> optionsToChoose = new LinkedList<>(Arrays.asList(
                "Available actions (select letter)",
                "(A)dd places",
                "(R)emove places",
                "(F)orward",
                "(B)ackward",
                "(L)ist Places",
                "(Q)uit"));

        ListIterator<String> iterator = optionsToChoose.listIterator();
        System.out.printf("%n%s%s%n%n", " ".repeat(lengthOfMessage / 2), iterator.next());

        while (iterator.hasNext()) {
            System.out.printf("%s%s%n", " ".repeat(lengthOfMessage / 2), iterator.next());
        }

        System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "Please select an option: ");
    }

    private static String checkDecisionBackQuit() throws InterruptedException {
        if (valueFromUser.equalsIgnoreCase("quit")) {
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "Quiting...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.exit(0);
        } else if (valueFromUser.equalsIgnoreCase("back")) {
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "Going back...");
            TimeUnit.SECONDS.sleep(1);
            return "true";
        }

        return "false";
    }

    private static int askForNumberItems(String action) throws InterruptedException {
        int numberOfPlaces = -1;

        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHeader("check places to visit");
            System.out.printf("%n%s%s%s%s", " ".repeat(lengthOfMessage / 2), "How many destinations to " , action , " (quit/back): ");
            valueFromUser = input.nextLine();

            if (Boolean.parseBoolean(checkDecisionBackQuit())) {
                return 0;
            } else {
                try {
                    numberOfPlaces = Integer.parseInt(valueFromUser);
                    if (numberOfPlaces <= 0) {
                        System.err.printf("%n%s%s%n", " ".repeat(lengthOfMessage / 2), "ERROR please use an integer greater than zero!");
                    } else {
                        return numberOfPlaces;
                    }
                } catch (NumberFormatException e) {
                    System.err.printf("%n%s%s%n", " ".repeat(lengthOfMessage / 2), "ERROR please use an integer greater than zero!");
                }
            }

            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static void askForSpecificPlaces(int nrOfItems, boolean toAdd) throws InterruptedException {
        int matchedIndex;
        int counter = 0;
        boolean ifSmall;
        boolean alreadyExists;
        ArrayList<String> answer;
        LinkedList<Place> itemsAlreadyInTheList = new LinkedList<>();
        LinkedList<Place> itemsAdded = new LinkedList<>();
        LinkedList<Place> itemsRemoved = new LinkedList<>();

        while (counter < nrOfItems) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHeader("check places to visit");

            if (toAdd) {
                System.out.printf("%n%s%s%s%s", " ".repeat(lengthOfMessage / 2), "Please input the place to add, like name, distance (quit/back) #",(counter + 1) ,": ");
            } else {
                System.out.printf("%n%s%s%s%s", " ".repeat(lengthOfMessage / 2), "Please input the place to remove, like name, distance (quit/back) #", (counter + 1), ": ");
            }

            valueFromUser = input.nextLine();

            if (Boolean.parseBoolean(checkDecisionBackQuit())) {
                break;
            } else {
                int shortCount = 0;
                answer = new ArrayList<>();

                for (String index : List.of(valueFromUser.toLowerCase().split(", "))) {
                    if (shortCount == 1) {
                        try {
                            answer.add(index.trim());
                        } catch (NumberFormatException e) {
                            System.err.printf("%n%s%s%n", " ".repeat(lengthOfMessage / 2), "ERROR please use a name and distance as integer or double separated by a comma to add or just a name to remove!");
                            TimeUnit.SECONDS.sleep(1);
                            return;
                        }
                    } else {
                        answer.add(index.trim());
                    }

                    shortCount += 1;
                }

                if ((answer.size()) != 2) {
                    System.err.printf("%n%s%s%n", " ".repeat(lengthOfMessage / 2), "ERROR please use a name and distance as integer or double separated by a comma to add or just a name to remove!");
                } else {
                    alreadyExists = false;
                    Place newPlace = new Place(StringUtils.capitalize(answer.get(0)), Double.parseDouble(answer.get(1)));

                    if (toAdd) {
                        for (Place p : placesToVisit) {
                            if (p.name().equalsIgnoreCase(newPlace.name())) {
                                itemsAlreadyInTheList.offer(newPlace);
                                alreadyExists = true;
                            }
                        }
                        if (!alreadyExists) {
                            ifSmall = false;
                            matchedIndex = 0;
                            for (Place element : placesToVisit) {
                                if (newPlace.distance() < element.distance()) {
                                    placesToVisit.add(matchedIndex, newPlace);
                                    itemsAdded.offer(newPlace);
                                    ifSmall = true;
                                    break;
                                }
                                matchedIndex += 1;
                            }

                            if (!ifSmall) {
                                placesToVisit.offer(newPlace);
                                itemsAdded.offer(newPlace);
                            }
                        }
                    } else {
                        for (Place p : placesToVisit) {
                            if (p.name().equalsIgnoreCase(newPlace.name())) {
                                placesToVisit.remove(p);
                                itemsRemoved.offer(p);
                            }
                        }
                    }
                    counter += 1;
                }
            }
        }

        if ((toAdd) && ((!itemsAlreadyInTheList.isEmpty()) || (!itemsAdded.isEmpty()))) {
            System.out.printf("%n%s%s%s%s", " ".repeat(lengthOfMessage / 2), itemsAlreadyInTheList.size() ," items already in the list: ", itemsAlreadyInTheList);
            System.out.printf("%n%s%s%s", " ".repeat(lengthOfMessage / 2), "We added the following elements: ", itemsAdded);
        } else if (!itemsRemoved.isEmpty()) {
            System.out.printf("%n%s%s%s", " ".repeat(lengthOfMessage / 2), "We removed the following elements: ", itemsRemoved);
        }

        System.out.printf("%n%s%s%s", " ".repeat(lengthOfMessage / 2), "We have the current places in the itinerary: ", placesToVisit);
        TimeUnit.SECONDS.sleep(2);
    }

    private static void addPlacesInTheList() throws InterruptedException {
        int numberOfItemsToAdd = askForNumberItems("add");
        askForSpecificPlaces(numberOfItemsToAdd, true);
    }

    private static void removePlacesFromTheList() throws InterruptedException {
        if (placesToVisit.isEmpty()) {
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "Cannot remove, there are no places in the itinerary!");
            TimeUnit.SECONDS.sleep(2);
        } else {
            int numberOfItemsToRemove = askForNumberItems("remove");
            askForSpecificPlaces(numberOfItemsToRemove, false);
        }
    }

    private static void printPlaces() throws InterruptedException {
        if (placesToVisit.isEmpty()) {
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "No places in the itinerary!");
            TimeUnit.SECONDS.sleep(1);
            return;
        }

        if (currentPlace == null) {
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "We didn't start the journey yet, we need to move forward!");
        } else {
            ListIterator<Place> iterator = placesToVisit.listIterator();

            System.out.printf("%n%s%s%s", " ".repeat(lengthOfMessage / 2), "We are in ", currentPlace.name());
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "All places on the itinerary: ");

            StringBuilder itineraryToPrint = new StringBuilder();

            itineraryToPrint.append(iterator.next());

            while (iterator.hasNext()) {
                itineraryToPrint.append(" -> ").append(iterator.next());
            }

            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), itineraryToPrint);
        }

        TimeUnit.SECONDS.sleep(2);
    }

    private static void moveForward() throws InterruptedException {
        iteratorForPlaces = placesToVisit.listIterator(currentIndex);

        if (placesToVisit.isEmpty()) {
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "There are no places in the list, cannot move forward!");
        } else {
            if (currentPlace == null) {
                currentPlace = iteratorForPlaces.next();
                currentIndex = iteratorForPlaces.nextIndex();
                System.out.printf("%n%s%s%s%s", " ".repeat(lengthOfMessage / 2), "Starting the journey from ", currentPlace, "! " );
            } else if (iteratorForPlaces.hasNext()) {
                if (!iteratorForPlaces.hasPrevious()) {
                    iteratorForPlaces.next();
                }

                currentPlace = iteratorForPlaces.next();
                currentIndex = iteratorForPlaces.nextIndex();
                System.out.printf("%n%s%s%s%s%s%s", " ".repeat(lengthOfMessage / 2), "Moving forward to ", currentPlace.name(), ", distance ", currentPlace.distance(), "! ");

                if (!iteratorForPlaces.hasNext()) {
                    System.out.printf("%n%s%s%s", " ".repeat(lengthOfMessage / 2), currentPlace.name(), " is the end of the line.");
                }
            }
        }

        TimeUnit.SECONDS.sleep(2);
    }

    private static void moveBackward() throws InterruptedException {
        iteratorForPlaces = placesToVisit.listIterator(currentIndex);

        if (placesToVisit.isEmpty()) {
            System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "There are no places in the list, cannot move backward!");
        } else {
            if (currentPlace == null) {
                System.out.printf("%n%s%s", " ".repeat(lengthOfMessage / 2), "We didn't start the journey. You need to move forward at least one place in order to be able to go back!" );
            } else if (iteratorForPlaces.hasPrevious()) {
                if (!iteratorForPlaces.hasNext()) {
                    iteratorForPlaces.previous();
                }

                currentPlace = iteratorForPlaces.previous();
                currentIndex = iteratorForPlaces.nextIndex();

                System.out.printf("%n%s%s%s%s%s%s", " ".repeat(lengthOfMessage / 2), "Moving backward to ", currentPlace.name(), ", distance ", currentPlace.distance(), "! ");

                if (!iteratorForPlaces.hasPrevious()) {
                    System.out.printf("%n%s%s%s", " ".repeat(lengthOfMessage / 2), currentPlace.name(), " is the start of the itinerary!");
                }
            }
        }

        TimeUnit.SECONDS.sleep(2);
    }

    public static void runPlacesMenu() throws InterruptedException {
        while (!valueFromUser.equals("q")) {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            printHeader("check places to visit");
            printMenu();

            String errorMessage = String.format("%n%s%s%n", " ".repeat(lengthOfMessage / 2),
                    "ERROR - please use one of those options from the menu!");

            valueFromUser = input.nextLine();

            if (valueFromUser.length() > 1) {
                System.out.print(errorMessage);
                TimeUnit.SECONDS.sleep(2);
            } else {
                String optionSelected = String.valueOf(valueFromUser.toLowerCase().charAt(0));

                switch (optionSelected) {
                    case "a" -> addPlacesInTheList();
                    case "r" -> removePlacesFromTheList();
                    case "f" -> moveForward();
                    case "b" -> moveBackward();
                    case "l" -> printPlaces();
                    case "q" -> {
                        System.out.printf("%n%s%s%n%n", " ".repeat(lengthOfMessage / 2), "Quiting...");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                    }
                    default -> {
                        System.out.print(errorMessage);
                        TimeUnit.SECONDS.sleep(2);
                    }
                }
            }
        }
    }
}