package WithJava.PlayingASong.src.main.java.playing.playingasong;

import java.util.List;
import java.util.ArrayList;

public abstract class Album {
    private int emptySpaces;
    private List<Song> listOfSongs;

    protected Album(int emptySpaces) {
        this.listOfSongs = new ArrayList<>();
        this.emptySpaces = setEmptySpaces(emptySpaces, true);
    }

    public abstract void addSong(Song song);

    public void removeSong(Song song) {
        boolean exists = false;

        for (int i = 0; i < getListOfSongs().size(); i++) {
            if (song.equals(getListOfSongs().get(i))) {
                exists = true;
                break;
            }
        }

        if (exists) {
            getListOfSongs().remove(song);
        }
    }

    public int setEmptySpaces(int emptySpaces, boolean forConstructor) {
        int toReturn = AuxMethodsAndChecks.checkEmptySpaces(emptySpaces);

        if (!forConstructor) {
            this.emptySpaces = toReturn;
        }

        return toReturn;
    }

    public List<Song> getListOfSongs() {
        return listOfSongs;
    }

    public int numberOfSongs() {
        return listOfSongs.size();
    }

    @Override
    public String toString() {
        return "Album{" + "songs = " + listOfSongs + '}';
    }
}
