package playing.mappingLocations;

public class River extends Line implements Mappable {
    private String name;

    protected River(String name, String locations, int emptySpaces) throws InterruptedException {
        super(emptySpaces, locations);
        this.name = Park.setName(name, emptySpaces);
    }

    public String getName() {
        return name;
    }

    @Override
    public void render() {
        System.out.printf("%n%s%s%s%s%s", " ".repeat(getEmptySpaces()), getName(), " as ", super.getClass().getSimpleName().toUpperCase(), " (" + getCoordinates() + ") ");
    }
}
