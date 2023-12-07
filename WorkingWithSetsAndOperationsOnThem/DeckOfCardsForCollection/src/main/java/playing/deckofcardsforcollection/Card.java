package playing.deckofcardsforcollection;

import java.util.*;
import java.security.SecureRandom;

enum Suit {
    Club, Diamond, Heart, Spade;

    private static int index;

    static {
        index = 0;
    }

    public static Suit getSuit() {
        return Suit.values()[index++ % Suit.values().length];
    }

    public static Suit retrieveTypeOfSuit(String typeOfSuit) {
        return Suit.valueOf(typeOfSuit);
    }
}

enum Face {
     Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;

     private static int indexOfFace;

     static {
         indexOfFace = 0;
     }

     public static Face getFace() {
         return Face.values()[indexOfFace++ % Face.values().length];
     }

    public static Face retrieveTypeOfFace(String typeOfFace) {
        int valueToUseForNumbers;
        Face valueToReturn = Face.values()[0];

        try {
             valueToUseForNumbers = Integer.parseInt(typeOfFace);
             if ((valueToUseForNumbers - 2) < 0 || ((valueToUseForNumbers - 2) >= 14)) {
                 valueToReturn = Face.values()[0];
             } else {
                 if (valueToUseForNumbers >= 12) {
                     valueToReturn = Face.values()[valueToUseForNumbers - 3];
                 } else {
                     valueToReturn = Face.values()[valueToUseForNumbers - 2];
                 }
             }
         } catch (NumberFormatException e) {
            for (int i = Face.values().length - 1; i > (Face.values().length - 5); i--) {
                if (String.valueOf(Face.values()[i]).contains(typeOfFace)) {
                    valueToReturn = Face.values()[i];
                    break;
                }
            }
        }

        return valueToReturn;
    }
}

public class Card implements Comparable <Card> {
    public static class CardComparator implements Comparator<Card> {
        private String typeOfComparison;

        public CardComparator() {
            this("rank");
        }

        public CardComparator(String typeOfComparison) {
            this.typeOfComparison = typeOfComparison;
        }

        private int toSearchForSuit(Card objectToSearchFor) {
            int toReturn = 0;

            for (int j = 0; j < Suit.values().length; j++) {
                if (objectToSearchFor.suit.toString().equals(Suit.values()[j].toString())) {
                    toReturn += j;
                }
            }

            return toReturn;
        }

        @Override
        public int compare(Card o1, Card o2) {
            if ("face".equals(typeOfComparison)) {
                int indexOfA = 0, indexOfB = 0;

                for (int i = 0; i < Face.values().length; i++) {
                    if (o1.face.equals(Face.values()[i])) {
                        indexOfA = i;
                    }

                    if (o2.face.equals(Face.values()[i])) {
                        indexOfB = i;
                    }

                    if ((indexOfA != 0) && (indexOfB != 0)) {
                        break;
                    }
                }

                return indexOfB - indexOfA;
            } else if ("faceAndSuit".equals(typeOfComparison)) {
                int indexToUseForComparisonA = 0;
                int indexToUseForComparisonB = 0;

                for (int i = 0; i < Face.values().length; i++) {
                    if (o1.face.equals(Face.values()[i])) {
                        indexToUseForComparisonA = (i + toSearchForSuit(o1));
                    }

                    if (o2.face.equals(Face.values()[i])) {
                        indexToUseForComparisonB = (i + toSearchForSuit(o2));
                    }

                    if (indexToUseForComparisonA != 0 && indexToUseForComparisonB != 0) {
                        break;
                    }
                }

                return indexToUseForComparisonB - indexToUseForComparisonA;
            }

            return o2.rank - o1.rank;
        }
    }

    private Suit suit;
    private Face face;
    private Integer rank;
    private static final int NUMBER_OF_CARDS;

    static {
        NUMBER_OF_CARDS = 52;
    }

    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
        this.rank = setRankFromFace(this.face);
    }

    public Card(String suit, String face) {
        this.suit = Suit.retrieveTypeOfSuit(suit);
        this.face = Face.retrieveTypeOfFace(face);
        this.rank = setRankFromFace(this.face);
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    public Integer getRank() {
        return rank;
    }

    public static Comparator<Card> sortRankSuit() {
        return Comparator.comparing(Card::getRank).reversed().thenComparing(Card::getSuit);
    }

    private int setRankFromFace(Face givenFace) {
        int valueToReturn = 1;

        for (int i = 0; i < Face.values().length; i++) {
            if (givenFace.equals(Face.values()[i])) {
                valueToReturn = i;
                break;
            }
        }

        return valueToReturn;
    }

    private static String existsSuit(String suit) throws InterruptedException {
        String toReturn;

        try {
            toReturn = String.valueOf(Suit.valueOf(suit));
        } catch (IllegalArgumentException e) {
            toReturn = "Club";
            System.out.printf("%n%s%s%n", suit ," doesn't exists as suit. It will default to Club!");
            Thread.sleep(1000);
        }

        return toReturn;
    }

    public static Card getNumericCard(String suit, int face) throws InterruptedException {
        int toBeUsedAsFace = 2;

        if ((face < 2) || (face > 10)) {
            System.out.printf("%n%s%s%n", face ," doesn't exists as face. It will default to Two!");
            Thread.sleep(1000);
        } else {
            toBeUsedAsFace = face;
        }

        return new Card(Card.existsSuit(existsSuit(suit)), String.valueOf(toBeUsedAsFace));
    }

    public static Card getFaceCard(String suit, String face) throws InterruptedException {
        String toBeUsedAsFaceInitial = "Jack";
        boolean exists = false;

        for (int i = Face.values().length - 1; i > Face.values().length - 5; i--) {
            if (String.valueOf(Face.values()[i]).contains(face)) {
                toBeUsedAsFaceInitial = String.valueOf(Face.values()[i]);
                exists = true;
                break;
            }
        }

        if (!exists) {
            System.out.printf("%n%s%s%n", face, " didn't exists. It will default to Jack!");
            Thread.sleep(1000);
        }

        return new Card(Card.existsSuit(existsSuit(suit)), String.valueOf(toBeUsedAsFaceInitial));
    }

    public static List<Card> getStandardDeck() {
        int count = 0;
        List<Card> deckOfCards = new ArrayList<>(NUMBER_OF_CARDS);

        while (count < NUMBER_OF_CARDS) {
            deckOfCards.add(new Card(Suit.values()[count / 13], Face.values()[count % 13]));
            count++;
        }

        return deckOfCards;
    }

    public static void printDeck(String description, List<Card> deckOfCards, int rowCount) {
        System.out.printf("%n%s%n%n", description);
        int limit = (NUMBER_OF_CARDS / rowCount);

        System.out.printf("%-25s", deckOfCards.get(0));

        for (int i = 1; i < deckOfCards.size(); i++) {
            if (i % limit == 0) {
                System.out.println();
            }

            System.out.printf("%-25s", deckOfCards.get(i));
        }
    }

    public static void printDeck(List<Card> cards) {
        printDeck("Current Deck", cards, 4);
    }

    public static List<Card> shuffleCards(List<Card> cards) {
        SecureRandom rnd = new SecureRandom();
        int index; Card temp;

        for (int i = 0; i < cards.size(); i++) {
            index = rnd.nextInt(NUMBER_OF_CARDS);

            temp = cards.get(i);
            cards.set(i, cards.get(index));
            cards.set(index, temp);
        }

        return cards;
    }

    public static List<Card> shuffleCards(List<Card> cards, int howManyShuffles) {
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < howManyShuffles; i++) {
            Collections.shuffle(cards, rnd);
        }

        return cards;
    }

    @Override
    public boolean equals(Object o) {
        boolean valueToReturn;

        if (this == o ) {
            valueToReturn = true;
        } else if (o == null || getClass() != o.getClass()) {
            valueToReturn = false;
        } else {
            Card card = (Card) o;
            valueToReturn = (card.suit.toString().equals(suit.toString()) &&
                    face.toString().equals(card.face.toString()) && Objects.equals(rank, card.rank));
        }

        return valueToReturn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, face, rank);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s%s", face.toString(), " of " ,suit.toString(), " (", rank, ")");
    }

    @Override
    public int compareTo(Card o) {
        return o.rank - this.rank;
    }
}
