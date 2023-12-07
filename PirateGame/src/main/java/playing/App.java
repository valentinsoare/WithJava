package playing;

import playing.game.GameConsole;
import playing.pirate.PirateGame;

public class App {
    public static void main(String[] args) {
        GameConsole<PirateGame> game =
                new GameConsole<>(new PirateGame("Pirate Game"));

        System.out.printf("%n");

        game.addPlayer();
        game.playGame(0);
    }
}
