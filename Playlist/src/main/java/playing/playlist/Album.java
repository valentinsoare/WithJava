package playing.playlist;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;
    private String artist;
    private SongList songsOfTheAlbum;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songsOfTheAlbum = new SongList();
    }

    private static class SongList {
        private List<Song> listWithSongs;

        private SongList() {
            this.listWithSongs = new ArrayList<>();
        }

        private Song findSong(String nameOfSong) {
            for (Song element : listWithSongs) {
                if (nameOfSong.equalsIgnoreCase(element.getNameOfSong())) {
                    return element;
                }
            }
            return null;
        }

        private boolean addSongToList(Song songToBeAdded) {
            if (findSong(songToBeAdded.getNameOfSong()) != null) {
                return false;
            }
            listWithSongs.add(songToBeAdded);
            return true;
        }

        private Song findSong(int trackNumber) {
            if ((trackNumber < 1) || (trackNumber > listWithSongs.size())) {
                return null;
            } else {
                return listWithSongs.get(trackNumber - 1);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public boolean addSong(String nameOfSong, double durationOfSong) {
        return songsOfTheAlbum.addSongToList(new Song(nameOfSong, durationOfSong));
    }

    public List<Song> getListOfSongs() {
        return songsOfTheAlbum.listWithSongs;
    }

    @Override
    public String toString() {
        StringBuilder toBePrinted = new StringBuilder();

        toBePrinted.append(String.format("%nAlbum: %s, artist: %s%n", name, artist));

        for (int i = 0; i < songsOfTheAlbum.listWithSongs.size(); i++) {
            toBePrinted.append(String.format("%n%s%s%s", " ".repeat(2), i+1 + ". ", songsOfTheAlbum.listWithSongs.get(i)));
        }

        return toBePrinted.toString();
    }
}