package playing.warcraftsimulation;

import java.util.*;
import java.security.SecureRandom;

public abstract class Hero {
    private String name;
    private int id;
    private double hp;
    private double attackPower;
    private int level;
    private boolean isIdle;
    private static List<Hero> listWithHeroes = new ArrayList<>();
    private SecureRandom rnd;

    protected Hero(String name, double hp, double attackPower) {
        this.name = setName(name, true);
        this.id = setId(true, true);
        this.hp = hp;
        this.attackPower = attackPower;
        this.level = 1;
        this.isIdle = false;
        this.rnd = new SecureRandom();

        rememberHero();
    }

    public void rememberHero() {
        listWithHeroes.add(this);
    }

    public abstract void levelUp();

    public void attack(Hero hero) {
        if (!this.isIdle()) {
            System.out.printf("%n%s%s%s%s%s%s%s", " ".repeat(2), this.getName(), " attacks ", hero.getName(), " with ", this.getAttackPower(), " attack power ");
            hero.receiveHit(this.getAttackPower());

            if (this.getHp() > 0) {
                this.levelUp();
                this.setIdle(true);
            }

            if (hero.getHp() > 0) {
                hero.levelUp();
                hero.setIdle(true);
            }
        }
    }

    public abstract void receiveHit(double hitPower);

    @Override
    public boolean equals(Object obj) {
        boolean toReturn;

        if (this == obj) {
            toReturn = true;
        } else if ((obj == null) || getClass() != obj.getClass()) {
            toReturn = false;
        } else {
            Hero hero = (Hero) obj;
            toReturn = (id == hero.id && Double.compare(hero.hp, hp) == 0 && Double.compare(hero.attackPower, attackPower) == 0
                    && level == hero.level && isIdle == hero.isIdle);
        }

        return toReturn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hp, attackPower, level, isIdle);
    }

    @Override
    public String toString() {
        return name + ", Unknown, " + getHp() + " HP, " + getAttackPower() + " attack, " + "level " + getLevel();
    }

    private int generateID() {
        SecureRandom rnd = new SecureRandom();
        int generatedId = 0;
        boolean alreadyExists = true, toExit = false;

        while (!toExit) {
            generatedId = rnd.nextInt(99999) + 100000;

            for (Hero element : listWithHeroes) {
                if (element.getId() == generatedId) {
                    alreadyExists = true;
                    break;
                }
            }

            if (alreadyExists) {
                alreadyExists = false;
            } else {
                toExit = true;
            }
        }

        return generatedId;
    }

    public int getId() {
        return id;
    }

    public int setId(boolean forConstructor, boolean toGenerateID) {
        int idToUse;

        if (toGenerateID) {
            idToUse = generateID();
        } else {
            idToUse = getId() + 1;
        }

        if (!forConstructor) {
            this.id = idToUse;
        }

        return idToUse;
    }

    public double getHp() {
        return hp;
    }

    public double getAttackPower() {
        return attackPower;
    }

    public int getLevel() {
        return level;
    }

    public static List<Hero> getListWithHeroes() {
        Collections.shuffle(listWithHeroes);

        return listWithHeroes;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAttackPower(double attackPower) {
        this.attackPower = attackPower;
    }

    public int getElementToSimulateChances(int upperLimit) {
        return rnd.nextInt(upperLimit);
    }

    public String getName() {
        return name;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public String setName(String name, boolean forConstructor) {
        StringBuilder capitalizedName = new StringBuilder();

        if (name.isBlank()) {
            capitalizedName.append("Unknown");
        } else {
            for (String element : new ArrayList<>(Arrays.asList(name.split(" ")))) {
                capitalizedName.append(element.toUpperCase().charAt(0) + element.substring(1)).append(" ");
            }
        }

        if (!forConstructor) {
            this.name = capitalizedName.toString().trim();
        }

        return capitalizedName.toString().trim();
    }

    public abstract String statsAtTheEndOfARound();

    public boolean removeHero(Hero hero) {
            for (int i = 0; i < getListWithHeroes().size(); i++) {
                if (getListWithHeroes().get(i).equals(hero)) {
                    getListWithHeroes().remove(hero);
                    return true;
                }
            }

            return false;
    }
}
