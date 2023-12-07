package WithJava.storagePlayers.src.main.java.playing.storageplayers;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Player implements Saveable {
    private String name;
    private String weapon;
    private int hitPoints;
    private int strength;

    public Player(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.weapon = "Sword";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public List<String> write() {
        return new ArrayList<>(Arrays.asList("name", "hitPoints", "strength", "weapon"));
    }

    @Override
    public void read(List<String> toStore) {
        List<String> temp = new ArrayList<>(Arrays.asList(this.getName(), String.valueOf(this.getHitPoints()),
                String.valueOf(this.getStrength()), getWeapon()));

        if ((toStore != null) && (!toStore.isEmpty())) {
            for (int i = 0; i < temp.size(); i++) {
                toStore.set(i, toStore.get(i) + ": " + temp.get(i).trim());
            }
        } else {
            System.out.printf("%n%s%s", " ".repeat(2), "Given list is empty!");
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints + '\'' +
                ", strength=" + strength +
                ", weapon=" + weapon +
                '}';
    }
}
