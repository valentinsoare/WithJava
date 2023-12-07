package playing.warcraftsimulation;

public class Orc extends Hero implements Strength {
    protected Orc(String name) {
        super(name,200, 15);
    }

    @Override
    public void levelUp() {
        if (getLevel() < 100) {
            setHp(getHp() + 2);
            setAttackPower(getAttackPower() + 8);
            setLevel(getLevel() + 1);
        }
    }

    @Override
    public void receiveHit(double hitPower) {
        double dmg;
        boolean toCheckCritical = isCriticalHit();

        if (toCheckCritical) {
            System.out.printf("%s", "(CRIT)");
            dmg = 2 * hitPower;
        } else {
            dmg = hitPower;
        }

        setHp(getHp() - dmg);
    }

    @Override
    public boolean isCriticalHit() {
        return (this.getElementToSimulateChances(2) == 0);
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
