package WithJava.mappingLocations.src.main.java.playing.mappingLocations;

public class MiniAppMap {
    public static void main(String[] args) throws InterruptedException {
        Park yellowStone = new Park("Yellowstone National Park", "44.4882, -110.5916", 2);
        River mississipi = new River("Mississippi River", "47.2160, -95.2348; 35.1556, -90.0659; 29.1566, -89.2495", 2);

        Layer firstL = new Layer<>();

        firstL.addElement(yellowStone);
        firstL.addElement(mississipi);

        firstL.renderLayer();
    }
}
