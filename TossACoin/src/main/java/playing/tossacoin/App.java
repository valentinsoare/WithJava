package WithJava.TossACoin.src.main.java.playing.tossacoin;

import java.util.*;
import java.util.concurrent.TimeUnit;

enum Coin {
    HEADS, TAIL;

    static final List<Coin> sides = new LinkedList<>(List.of(values()));
    static ListIterator<Coin> iterator = sides.listIterator();

    public static Coin getSide() {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            while (iterator.hasPrevious()) {
                iterator.previous();
            }
        }

        return getSide();
    }
}

public class App {
    private static Random rnd = new Random();
    private static List<Integer> coinTossesSides;
    private static Scanner input = new Scanner(System.in);

    private static void printHeader(String givenMessage) {
        String processedMessage = String.format("%n%s%s %s %s", " ".repeat(15), "#".repeat(3), givenMessage.trim().toUpperCase(), "#".repeat(3));
        System.out.printf("%s%n%s%s", processedMessage, " ".repeat(7),"-".repeat(processedMessage.length()));
    }

    private static void printMenuCoin() {
        List<String> options = new ArrayList<>(List.of("[T]oss Coin", "[P]rint Total Counter of Tosses", "[Q]uit"));
        printHeader("toss a coin");

        for (String element : options) {
            System.out.printf("%n%s%s", " ".repeat(7), element);
        }

        System.out.printf("%n%n%s%s", " ".repeat(7), "Choose an option: ");
    }

    private static void runTossingCoin() throws InterruptedException {
        coinTossesSides = new ArrayList<>(List.of(0, 0));

        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            printMenuCoin();
            String optionFromUser = input.nextLine();

            String errorMessage = String.format("%n%s%s%n", " ".repeat(7), "ERROR please select a valid option!");

            if (optionFromUser.trim().length() > 1) {
                System.out.printf(errorMessage);
                TimeUnit.SECONDS.sleep(2);
            } else {
                String processedMessage = optionFromUser.toLowerCase().trim();

                switch (processedMessage) {
                    case "t" -> tossingCoin();
                    case "p" -> printingCounterTossingCoin();
                    case "q" -> {
                        System.out.printf("%n%s%s%n", " ".repeat(7), "Exiting...");
                        TimeUnit.SECONDS.sleep(1);
                        return;
                    }
                    default -> {
                        System.out.printf(errorMessage);
                        TimeUnit.SECONDS.sleep(2);
                    }
                }
            }
        }
    }

    private static void tossingCoin() throws InterruptedException {
        String valueFromUser = "";
        int numberOfTossesToDo;
        List<Integer> tempTosses;

        while (true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            printHeader("toss a coin");

            System.out.printf("%n%s%s", " ".repeat(7), "How many times you want to toss a coin (back/quit): ");
            valueFromUser = input.nextLine();

            if (valueFromUser.equals("quit")) {
                System.out.printf("%n%s%s%n", " ".repeat(7), "Exiting...");
                TimeUnit.SECONDS.sleep(1);
                System.exit(0);
            } else if (valueFromUser.equals("back")) {
                System.out.printf("%n%s%s%n", " ".repeat(7), "Going back to main menu...");
                TimeUnit.SECONDS.sleep(1);
                break;
            } else {
                try {
                    int index;
                    tempTosses = new ArrayList<>(List.of(0, 0));
                    numberOfTossesToDo = Integer.parseInt(valueFromUser);

                    for (int j = 0; j < numberOfTossesToDo; j++) {
                        index = rnd.nextInt(2);
                        tempTosses.set(index, (tempTosses.get(index) + 1));
                    }

                    coinTossesSides.set(0, (coinTossesSides.get(0) + tempTosses.get(0)));
                    coinTossesSides.set(1, (coinTossesSides.get(1) + tempTosses.get(1)));

                    System.out.printf("%n%sOn these %s tosses, we had %s HEADS and %s TAILS!",
                            " ".repeat(7), numberOfTossesToDo, tempTosses.get(0), tempTosses.get(1));
                    TimeUnit.SECONDS.sleep(2);
                } catch (NumberFormatException e) {
                    System.err.printf("%n%s%s%n", " ".repeat(7), "ERROR please use an integer for number of tosses!");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }

    private static void printingCounterTossingCoin() throws InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();

        printHeader("toss a coin");

        System.out.printf("%n%sTOTAL counter of tosses: %s HEADS and %s TAILS.%n", " ".repeat(7), coinTossesSides.get(0), coinTossesSides.get(1));
        TimeUnit.SECONDS.sleep(2);
    }


    public static void main (String[] args) throws InterruptedException {
        runTossingCoin();
    }
}
