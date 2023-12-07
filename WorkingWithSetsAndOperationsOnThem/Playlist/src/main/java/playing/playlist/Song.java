package playing.playlist;

import java.util.Comparator;
import java.util.Objects;

public class Song {
    public static class SongComparator<T extends Song> implements Comparator<Song> {
        private String comparatorType;

        public SongComparator() {
            this("nameOfSong");
        }

        public SongComparator(String comparatorType) {
            this.comparatorType = comparatorType;
        }

        @Override
        public int compare(Song o1, Song o2) {
            if ("durationOfSong".equalsIgnoreCase(comparatorType)) {
                return Double.compare(o1.getDurationOfSong(), o2.getDurationOfSong());
            } else {
                return o1.getNameOfSong().compareTo(o2.getNameOfSong());
            }
        }
    }

    private String nameOfSong;
    private double durationOfSong;

    public Song(String nameOfSong, double durationOfSong) {
        this.nameOfSong = nameOfSong;
        this.durationOfSong = durationOfSong;
    }

    public String getNameOfSong() {
        return nameOfSong;
    }

    public double getDurationOfSong() {
        return durationOfSong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Song song = (Song) o;
            return (song.getNameOfSong().equalsIgnoreCase(this.getNameOfSong()) && song.getDurationOfSong() == this.getDurationOfSong());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfSong, durationOfSong);
    }

    @Override
    public String toString() {
        return String.format("title: %s, duration: %s", nameOfSong, durationOfSong);
    }
}
