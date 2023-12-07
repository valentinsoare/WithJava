package WithJava.WarcratSimulationInWriting.src.main.java.playing.warcraftsimulation;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Elf extends Hero implements Agility, Intelligence {
    protected Elf(String name) {
        super(name,50, 20);
    }

    @Override
    public boolean isAttackDodged() {
        List<Integer> chances = new ArrayList<>(Arrays.asList(0, 1));

        return chances.contains(getElementToSimulateChances(6));
    }

    @Override
    public void levelUp() {
        if (getLevel() < 100) {
            boolean toLevelUpWithChances = isEvolving();

            if (toLevelUpWithChances) {
                setHp(getHp() + 30);
                setAttackPower(getAttackPower() + 30);
                setLevel(getLevel() + 2);
            } else {
                setHp(getHp() + 15);
                setAttackPower(getAttackPower() + 15);
                setLevel(getLevel() + 1);
            }
        }
    }

    @Override
    public void receiveHit(double hitPower) {
        boolean toDodge = isAttackDodged();

        if (!toDodge) {
            setHp(getHp() - hitPower);
        }
    }

    @Override
    public boolean isEvolving() {
        List<Integer> chances = new ArrayList<>(Arrays.asList(0, 1, 2));

        return chances.contains(getElementToSimulateChances(10));
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
