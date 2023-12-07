package WithJava.WarcratSimulationInWriting.src.main.java.playing.warcraftsimulation;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Undead extends Hero implements Strength, Agility {
    protected Undead(String name) {
        super(name,300, 5);
    }

    @Override
    public boolean isAttackDodged() {
        List<Integer> chances = new ArrayList<>(Arrays.asList(0, 1, 2));
        return chances.contains(getElementToSimulateChances(20));
    }

    @Override
    public void levelUp() {
        if (getLevel() < 100) {
            setAttackPower(getAttackPower() + 6);
            setLevel(getLevel() + 1);
        }
    }

    @Override
    public void receiveHit(double hitPower) {
        boolean toDodge = isAttackDodged();

        if (!toDodge) {
            if (isCriticalHit()) {
                System.out.printf("%s", "(CRIT)");
                setHp(getHp() - (2 * hitPower));
            } else {
                setHp(getHp() - hitPower);
            }
        }
    }

    @Override
    public boolean isCriticalHit() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 1, 2));

        return elements.contains(getElementToSimulateChances(10));
    }

    @Override
    public String toString() {
        return String.format("%s".repeat(11), " ".repeat(2), getName(), ", ", getClass().getSimpleName(), ", ", getHp(), " HP, ", getAttackPower(), " attack, ", "level ", getLevel());
    }

    @Override
    public String statsAtTheEndOfARound() {
        String toReturn;

        if (isIdle()) {
            toReturn = String.format("%s%s%s%s%s%s%s%s%s", " ".repeat(2), getName(), " levels up: ", "HP = ", getHp(), ", attackPower = ", getAttackPower(), ", level = ", getLevel());
            setIdle(false);
        } else {
            toReturn = String.format("%s%s%s", " ".repeat(2), getName(), " does not level up");
        }

        return toReturn;
    }
}
