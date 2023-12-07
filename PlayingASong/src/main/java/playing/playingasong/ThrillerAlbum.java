package WithJava.PlayingASong.src.main.java.playing.playingasong;

public class ThrillerAlbum extends Album {
    protected ThrillerAlbum(int emptySpaces) {
        super(emptySpaces);
    }

    @Override
    public void addSong(Song song) {
        if ("michael jackson".equalsIgnoreCase(song.getComposer()) && (song.getId() % 2 == 0)) {
            getListOfSongs().add(song);
        }
    }
}
