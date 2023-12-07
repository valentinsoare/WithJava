package WithJava.PlayingASong.src.main.java.playing.playingasong;

public class DangerousAlbum extends Album {
    protected DangerousAlbum(int emptySpaces) {
        super(emptySpaces);
    }

    @Override
    public void addSong(Song song) {
        if (song.getId() % 2 != 0) {
            getListOfSongs().add(song);
        }
    }
}
