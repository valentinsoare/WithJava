package WithJava.DeckOfCardsForCollection.src.main.java.playing.deckofcardsforcollection;

import java.util.*;

public class App {
    public static void main( String[] args ) throws InterruptedException {
        Comparator<Card> sorterCards = new Card.CardComparator("faceAndSuit");

        List<Card> allCards = Card.getStandardDeck();
//        Card.shuffleCards(allCards);

        Card.printDeck(allCards);

        //-----------------------------
        Card[] cardArray = new Card[13];
        Card aceOfHearts = new Card("Heart", "A");
        Card kingOfClub = new Card("Club", "K");

        Arrays.fill(cardArray, aceOfHearts);
        System.out.println();
//        Card.printDeck("Aces of Hearts", Arrays.asList(cardArray), 1);

        List<Card> newCards = new ArrayList<>(52);

        System.out.println();

        List<Card> kingOfClubs = Collections.nCopies(13, kingOfClub);
        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);

        Collections.addAll(newCards, cardArray);

        Collections.copy(newCards, acesOfHearts);

        newCards = List.copyOf(acesOfHearts);


        Card.printDeck("AAAA", allCards, 8);

        System.out.println("\n\n");

        allCards.sort(sorterCards);





        List<Card> kings = new ArrayList<>(allCards.subList(44, 48));
        List<Card> queens = new ArrayList<>(allCards.subList(40, 44));

        System.out.println(kings);
        System.out.println(queens);

        int indexOfSubListKings = Collections.indexOfSubList(allCards, kings);
        int indexOfSubListQueens = Collections.indexOfSubList(allCards, queens);

        System.out.println(indexOfSubListKings);
        System.out.println(indexOfSubListQueens);

        System.out.println(new HashSet<>(allCards).containsAll(kings));


        System.out.println(Collections.disjoint(kings, queens));
        System.out.println(Collections.disjoint(kings, allCards));



        System.out.println();
        Card queenOfSpade = new Card("Spade", "Q");

        int indexOfQueenOfSpade = Collections.binarySearch(allCards, queenOfSpade, sorterCards);
        int secondIndexOfQueenOfSpade = allCards.indexOf(queenOfSpade);

        System.out.println(allCards.get(indexOfQueenOfSpade));
        System.out.println(allCards.get(secondIndexOfQueenOfSpade));


        Card kingsOfSpade = new Card("Spade", "K");
        Card aceOfClub = new Card ("Club", "A");

        Collections.replaceAll(allCards, kingsOfSpade, aceOfClub);
        System.out.println();
        System.out.println(allCards);


        System.out.println(Collections.frequency(allCards, aceOfClub));

        System.out.println(Collections.max(allCards));
        System.out.println(Collections.min(allCards));

//        var sortBySuit = Comparator.comparing(Card::face).thenComparing(Card::rank);

//        System.out.println(Collections.max(allCards, sortBySuit));


        List<Card> only13 = new ArrayList<>(allCards.subList(0, 13));

        System.out.println("\n\n");
        System.out.println(only13);

        Collections.rotate(only13, 3);
        System.out.println(only13);

        Collections.rotate(only13, -2);
        System.out.println(only13);



        List<Card> only5 = new ArrayList<>(allCards.subList(0, 6));

        System.out.println("\n\n");
        System.out.println(only5);

        for (int i = 0; i < only5.size() / 2; i++) {
            Collections.swap(only5, i, only5.size() - i - 1);
        }

        System.out.println(only5);
    }
}
