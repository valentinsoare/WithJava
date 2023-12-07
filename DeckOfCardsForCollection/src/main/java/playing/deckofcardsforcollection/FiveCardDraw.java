package WithJava.DeckOfCardsForCollection.src.main.java.playing.deckofcardsforcollection;

import java.util.List;
import java.util.ArrayList;


public class FiveCardDraw {
    private int numberOfPlayers;
    private List<PokerPlayer> registeredPlayer;
    private double valueOfOpeningBet;

    private static final int MAX_NUMBER_OF_PLAYERS;
    private static final int NUMBER_OF_CARDS_IN_THE_GAME;

    static {
        MAX_NUMBER_OF_PLAYERS = 4;
        NUMBER_OF_CARDS_IN_THE_GAME = 52;
    }

    public FiveCardDraw(int numberOfPlayers, int valueOfOpeningBet) {
        this.numberOfPlayers = numberOfPlayers;
        this.valueOfOpeningBet = valueOfOpeningBet;

        this.registeredPlayer = new ArrayList<>();
    }

}
