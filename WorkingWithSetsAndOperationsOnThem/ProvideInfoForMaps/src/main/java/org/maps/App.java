package org.maps;

import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main( String[] args ) {
        List<Mappable> mappables = new ArrayList<>();

        mappables.add(new Building("Primaria Bucuresti", UsageType.GOVERNMENT));
        mappables.add(new Building("Teatrul National;", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Arena Nationala", UsageType.SPORTS));

        for (Mappable element : mappables) {
            Mappable.mapIt(element);
        }
    }
}
