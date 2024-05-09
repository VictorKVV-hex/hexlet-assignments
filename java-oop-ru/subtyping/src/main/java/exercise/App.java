package exercise;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class App{

    public static void swapKeyValue(KeyValueStorage storage) throws IOException {
        Map<String, String> map = new HashMap<>();
        for(Map.Entry<String, String> entry : storage.toMap().entrySet()){
            String newKey = entry.getValue();
            String newValue = entry.getKey();
            storage.unset(entry.getKey());
            storage.set(newKey, newValue);
        }
    }
}
