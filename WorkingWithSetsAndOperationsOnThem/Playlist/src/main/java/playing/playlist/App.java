package playing.playlist;

import java.util.Comparator;

public class App {
    public static void main(String[] args) {
        Album album = new Album("Stormbringer", "Deep Purple");

        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.60);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);

        System.out.println(album);

        System.out.printf("%nSongs sorted by duration for album %s, artist %s%n", album.getName(), album.getArtist());

        Comparator<Song> songSorter = new Song.SongComparator<>("durationOfSong");
        album.getListOfSongs().sort(songSorter);

        System.out.println(album);
    }
}
