package playing.storageplayers;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Monster implements Saveable {
    private String name;
    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public List<String> write() {
        return new ArrayList<>(Arrays.asList("name", "hitPoints", "strength"));
    }

    @Override
    public void read(List<String> givenValues) {
        List<String> temp = new ArrayList<>(Arrays.asList(getName(), String.valueOf(getHitPoints()), String.valueOf(getStrength())));

        if ((givenValues != null) && (!givenValues.isEmpty())) {
            for (int i = 0; i < temp.size(); i++) {
                givenValues.set(i, givenValues.get(i) + ": " + temp.get(i).trim());
            }
        } else {
            System.out.printf("%n%s%s%n", " ".repeat(2), "Given list is empty!");
        }
    }

    @Override
    public String toString() {
        return "Monster {" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                '}';
    }
}
