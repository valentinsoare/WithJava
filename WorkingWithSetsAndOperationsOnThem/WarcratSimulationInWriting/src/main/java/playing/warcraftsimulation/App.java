package playing.warcraftsimulation;

import java.util.*;
import java.security.SecureRandom;

public class App {
    static SecureRandom rnd = new SecureRandom();

    public static void createHeroes() {
        Human demonHunter = new Human("Demon Hunter");
        Orc moruni = new Orc("Moruni");
        Elf illidanStormrage = new Elf("Illidan Stormrage");
        Undead cenarius = new Undead("Cenarius");
        Elf vralath = new Elf("Vralath");
        Human osdy = new Human("Osdy");
        Orc krettlic = new Orc("Krettlic");
        Orc lozil = new Orc("Lozil");
        Orc sethu = new Orc("Sethu");
        Orc belun = new Orc("Belun");
        Orc miarid = new Orc("Miarid");
        Orc paean = new Orc("Paean");
        Human rod = new Human("Rod");
        Human winfrid = new Human("Winfrid");
        Human kuwanyamtiwa = new Human("Kuwanyamtiwa");
        Human kowdu = new Human("Kowdu");
        Undead assi = new Undead("Assi");
        Undead liedros = new Undead("Liedros");
        Undead lorraina = new Undead("Lorraina");
        Undead fycina = new Undead("Fycina");
        Elf gandiuth = new Elf("Gandiuth");
    }

    public static void presentHeroes(List<Hero> heroesToBePrinted) {
        System.out.printf("%n%s%s%n%n", " ".repeat(2), "These are our warriors!");

        for (Hero element : heroesToBePrinted) {
            System.out.println(element);
        }
    }

    public static void printEndOfRoundStats(List<Hero> heroesToBePrintedWithStats, int round, List<Hero> deadHeroes) {
        System.out.printf("%n%n%s%s%s%s%n%n", " ".repeat(2), "Stats after ", round, " round");

        for (Hero e : heroesToBePrintedWithStats) {
            System.out.println(e.statsAtTheEndOfARound());
        }

        if (!deadHeroes.isEmpty()) {
            Hero last = deadHeroes.get(deadHeroes.size() - 1);
            System.out.printf("%s%s%s%s", " ".repeat(2), last.getName(), " died on this round at level ", last.getLevel());
        }
    }

    public static void countNumberOfIdles(List<Hero> heroes) {
        for (Hero element : heroes) {
            if (!element.isIdle()) {
                System.out.printf("%n%s%s%s", " ".repeat(2), element.getName(), " does nothing this round!");
            }
        }
    }

    public static void playTheGame(List<Hero> heroes) throws InterruptedException {
        int count, round = 1;
        List<Hero> excluded, deadHeroes = new ArrayList<>();
        Hero x, y;

        while (heroes.size() > 1) {
            count = 0;
            excluded = new ArrayList<>();

            System.out.printf("%n%n%s%s%s%s%n", " ".repeat(2), "Round ", round, " begins...");

            while (count < heroes.size() / 2) {
                x = heroes.get(count);
//                y = heroes.get(rnd.nextInt(heroes.size() / 2, heroes.size()));
                y = heroes.get(4);
                if (!excluded.contains(x)) {
                    excluded.add(x);
                }

                if (!excluded.contains(y)) {
                    excluded.add(y);
                    x.attack(y);

                    if (y.getHp() <= 0) {
                        heroes.remove(y);
                        deadHeroes.add(y);
                    }

                    count += 1;
                }
            }

            countNumberOfIdles(heroes);
            printEndOfRoundStats(heroes, round, deadHeroes);

            System.out.printf("%n%s%s%s%n", " ".repeat(2), "End of round ", round);
            Thread.sleep(3000);
            round += 1;
        }

        System.out.printf("%n%s%s%s%n", " ".repeat(2), "***Battle is won by ->", heroes.get(0));
    }

    public static void main( String[] args ) throws InterruptedException {
        createHeroes();
        List<Hero> heroes = Hero.getListWithHeroes();

        presentHeroes(heroes);
        playTheGame(heroes);
    }
}
