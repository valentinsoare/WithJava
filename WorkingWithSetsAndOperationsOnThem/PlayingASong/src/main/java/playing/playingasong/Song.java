package playing.playingasong;

import java.util.Objects;

public class Song {
    private static int lastID = 0;
    private String name;
    private int id;
    private String composer;
    private int emptySpaces;

    public Song(String name, int id, String composer, int emptySpaces) throws InterruptedException {
        this.name = setName(name, true);
        this.id = setId(id, true);
        this.composer = setComposer(composer, true);
        this.emptySpaces = setEmptySpaces(emptySpaces, true);
    }

    public String getName() {
        return name;
    }

    public String setName(String name, boolean forConstructor) throws InterruptedException {
        String returning = AuxMethodsAndChecks.checkNameProperly(name, emptySpaces);

        if (!forConstructor) {
            this.name = returning;
        }

        return returning;
    }

    public int setId(int newId, boolean forConstructor) {
        int valueToUse = newId;

        if (newId <= 0) {
            valueToUse = getLastID() + 1;
        }

        if (!forConstructor) {
            this.id = valueToUse;
        }

        setLastID(valueToUse);

        return valueToUse;
    }

    public String getComposer() {
        return composer;
    }

    public String setComposer(String composer, boolean forConstructor) throws InterruptedException {
        String nameToBeReturned = AuxMethodsAndChecks.checkFirstNameLastName(composer, emptySpaces);

        if (!forConstructor) {
            this.composer = nameToBeReturned;
        }

        return nameToBeReturned;
    }

    public int getEmptySpaces() {
        return emptySpaces;
    }

    public int setEmptySpaces(int emptySpaces, boolean forConstructor) {
        int returning = AuxMethodsAndChecks.checkEmptySpaces(emptySpaces);

        if (!forConstructor) {
            this.emptySpaces = returning;
        }

        return returning;
    }

    public static int getLastID() {
        return lastID;
    }

    public int getId() {
        return id;
    }

    public static void setLastID(int lastID) {
        Song.lastID = lastID;
    }

    @Override
    public boolean equals(Object obj) {
        boolean toReturn;

        if (this == obj) {
            toReturn = true;
        } else if (obj == null || getClass() != obj.getClass()) {
            toReturn = false;
        } else {
            Song song = (Song) obj;
            toReturn = (id == song.id && Objects.equals(name, song.name) && Objects.equals(composer, song.composer));
        }

        return toReturn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, composer);
    }

    @Override
    public String toString() {
        return "Song {" +
                "name = '" + name + '\'' +
                ", id = " + id +
                ", composer = '" + composer + '\'' + '}';
    }
}
