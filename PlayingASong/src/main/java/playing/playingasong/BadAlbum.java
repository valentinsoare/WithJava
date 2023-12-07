package WithJava.PlayingASong.src.main.java.playing.playingasong;

public class BadAlbum extends Album {
    protected BadAlbum(int emptySpaces) {
        super(emptySpaces);
    }

    @Override
    public void addSong(Song song) {
        if (song.getName().toCharArray().length == 3) {
            getListOfSongs().add(song);
        }
    }
}
