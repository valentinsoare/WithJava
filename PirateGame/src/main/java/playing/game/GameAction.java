package WithJava.PirateGame.src.main.java.playing.game;

import java.util.function.Predicate;

public record GameAction(char key, String prompt, Predicate<Integer> action) {
}
