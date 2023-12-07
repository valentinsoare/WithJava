package playing.playingcards;

public class App {
    public static void main( String[] args ) {
        DeckOfCards myCards = new DeckOfCards();
        myCards.shuffle();

        for (int i = 0; i < 52; i++) {
            System.out.printf("%-20s", myCards.dealCard());

            if ((i + 1) % 4 == 0) {
                System.out.println();
            }
        }
    }
}
