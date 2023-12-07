package WithJava.StudentsGenerics.src.main.java.playing.studentsgenerics;

import java.util.List;
import java.util.ArrayList;

public class QueryList <T extends Student & QueryItem> extends ArrayList<T> {
//    private List<T> items;

    public QueryList() {
    }

    public QueryList(List<T> items) {
//        this.items = items;
        super(items);
    }

    public QueryList<T> getMatches(String field, String value) {
        QueryList<T> matches = new QueryList<>();

        for (T item : this) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }

        return matches;
    }

    public static <S extends QueryItem> List<S> getMatches(List<S> items, String field, String value) {
        List<S> matches = new ArrayList<>();

        for (S item : items) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }

        return matches;
    }
}
