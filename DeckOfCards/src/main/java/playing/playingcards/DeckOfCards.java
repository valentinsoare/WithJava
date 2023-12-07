package WithJava.DeckOfCards.src.main.java.playing.playingcards;

import java.util.List;
import java.util.ArrayList;
import java.security.SecureRandom;

public class DeckOfCards {
    private static final int NUMBER_OF_CARDS = 52;
    private static final SecureRandom rnd = new SecureRandom();

    private int currentCard;
    private ArrayList<Card> deck;


    public DeckOfCards() {
        ArrayList<String> faces = new ArrayList<>(List.of("Aces", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"));
        ArrayList<String> suits= new ArrayList<>(List.of("Hearts", "Diamonds", "Clubs", "Spades"));

        int i = 0;
        deck = new ArrayList<>(NUMBER_OF_CARDS);

        while (i < NUMBER_OF_CARDS) {
            deck.add(i, new Card(faces.get(i % 13), suits.get(i / 13)));
            i++;
        }
    }

    public void shuffle() {
        currentCard = 0;

        for (int i = 0; i < deck.size(); i++) {
            int randomCard = rnd.nextInt(NUMBER_OF_CARDS);

            Card tmp = deck.get(i);
            deck.set(i, deck.get(randomCard));
            deck.set(randomCard, tmp);
        }
    }

    public Card dealCard() {
        if (currentCard < deck.size()) {
            return deck.get(currentCard++);
        } else {
            return null;
        }
    }
}
