package playing.storageplayers;

import java.util.List;

public interface Saveable {
    List<String> write();
    void read(List<String> toStore);
}
