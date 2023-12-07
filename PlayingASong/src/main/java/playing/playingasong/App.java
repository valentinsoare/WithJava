package playing.playingasong;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.security.SecureRandom;

public class App {
    public static void main( String[] args ) throws InterruptedException {
        Song dancingQueen = new Song("dancing queen" , -1, "ABBA", 2);
        Song backInBlack = new Song("back in black", -1, "AC/DC", 2);
        Song beautiful = new Song("beautiful", -1, "Christina Aguilera", 2);
        Song houseOfTheRisingSun = new Song("house of the rising sun", -1, "the animals", 2);
        Song whatAWonderfulWorld = new Song("what a wonderful world", -1, "louis armstrong", 2);
        Song billieJean = new Song("billie jean", -1, "michael jackson", 2);
        Song knockingOnHeavensDoor = new Song("knockin' on heaven's door", -1, "Guns N' Roses", 2);
        Song alive = new Song("alive", -1, "pearl jam", 2);
        Song youOughtaKnow = new Song("you oughta know", -1, "alanis morissette", 2);
        Song shivers = new Song("shivers", -1, "ed sheeran", 2);
        Song superGremlin = new Song("super gremlin", -1, "kodak black", 2);
        Song thatsWhatIWant = new Song("thats what i want", -1, "lil nas x", 2);
        Song needToKnow = new Song("need to know", -1, "doja cat", 2);
        Song titiMePregunto = new Song("Titi Me Pregunto", -1, "bad bunny", 2);
        Song not = new Song("not", -1, "steve lacy", 2);
        Song sunroof = new Song("Sunroof", -1, "Nicky youre & dazy", 2);


        List<Song> songsToWorkWith = new ArrayList<>(Arrays.asList(dancingQueen, backInBlack, beautiful, houseOfTheRisingSun, whatAWonderfulWorld,
                knockingOnHeavensDoor, billieJean, alive, youOughtaKnow, shivers, superGremlin, thatsWhatIWant, needToKnow, titiMePregunto,
                not, sunroof));

        DangerousAlbum danger = new DangerousAlbum(2);
        ThrillerAlbum thriller = new ThrillerAlbum(2);
        BadAlbum bad = new BadAlbum(2);

        List<Album> listOfAlbums = new ArrayList<>(Arrays.asList(bad, thriller, danger));
        SecureRandom rnd = new SecureRandom();
        boolean wasAdded; int valueToChose, initialSize;

        for (Song element : songsToWorkWith) {
            wasAdded = false;
            valueToChose = rnd.nextInt(listOfAlbums.size());
            initialSize = listOfAlbums.get(valueToChose).numberOfSongs();

            listOfAlbums.get(valueToChose).addSong(element);

            if (initialSize < listOfAlbums.get(valueToChose).numberOfSongs()) {
                wasAdded = true;
            } else {
                for (Album listOfAlbum : listOfAlbums) {
                    if (!listOfAlbum.getListOfSongs().contains(element)) {
                        initialSize = listOfAlbum.numberOfSongs();

                        listOfAlbum.addSong(element);

                        if (initialSize < listOfAlbum.numberOfSongs()) {
                            wasAdded = true;
                        }
                    }
                }
            }

            if (!wasAdded) {
                System.out.printf("%n%s%s%s", " ".repeat(element.getEmptySpaces()), "There is no album for ", element);
            }
        }

        System.out.printf("%n%n%s%s%n", " ".repeat(2), "Albums and Songs!");

        for (Album element : listOfAlbums) {
            System.out.printf("%n%s%s: %s", " ".repeat(2), element.getClass().getSimpleName(), element);
        }

        System.out.printf("%s", "\n".repeat(2));
    }
}
