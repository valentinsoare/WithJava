package WithJava.LetsVisit.src;

import org.apache.commons.lang3.StringUtils;

public record Place(String name, double distance) {
    public Place(String name) {
        this(name, 0);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", StringUtils.capitalize(name()), ", ", distance());
    }
}
