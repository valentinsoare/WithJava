package playing.mappingLocations;

import java.util.ArrayList;
import java.util.List;

public class Layer <T extends Mappable> {
    private List <T> elements;

    public Layer() {
        this.elements = new ArrayList<>();
    }

    public void addElement(T elementToBeAdded) {
        this.elements.add(elementToBeAdded);
    }

    public void renderLayer() {
        for (T element : elements) {
            element.render();
        }
    }
}
