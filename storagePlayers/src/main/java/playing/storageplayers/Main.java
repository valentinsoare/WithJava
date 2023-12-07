package WithJava.storagePlayers.src.main.java.playing.storageplayers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Monster warewolf = new Monster("warewolf", 20, 40);

        List<String> toUse = warewolf.write();
        warewolf.read(toUse);

        System.out.printf("%n%s%s", " ".repeat(2), toUse);
        System.out.printf("%n%s%s%n", " ".repeat(2), warewolf.toString());
    }
}
