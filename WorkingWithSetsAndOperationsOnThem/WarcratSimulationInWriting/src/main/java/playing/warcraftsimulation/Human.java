package playing.warcraftsimulation;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Human extends Hero implements Intelligence {
    protected Human(String name) {
        super(name,100, 10);
    }

    @Override
    public void levelUp() {
        if (getLevel() < 100) {
            boolean weCheckForChancesToDoubleLevelUp = isEvolving();

            if (weCheckForChancesToDoubleLevelUp) {
                setHp(getHp() + 20);
                setAttackPower(getAttackPower() + 24);
                setLevel(getLevel() + 2);
            } else {
                setHp(getHp() + 10);
                setAttackPower(getAttackPower() + 12);
                setLevel(getLevel() + 1);
            }
        }
    }

    @Override
    public void receiveHit(double hitPower) {
        setHp(getHp() - hitPower);
    }

    @Override
    public boolean isEvolving() {
        List<Integer> chances = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));

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
