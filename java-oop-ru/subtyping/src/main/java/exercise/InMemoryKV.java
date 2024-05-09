package exercise;

import java.util.Map;
import java.util.HashMap;

public class InMemoryKV implements KeyValueStorage{
    Map<String, String> map = new HashMap<>();

    public InMemoryKV(Map<String, String> initial) {
//        this.map = initial;
        map.putAll(initial);

    }

    @Override
    public void set(String key, String value) {
        map.put(key, value);
    }

    @Override
    public void unset(String key) {
        map.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(map);
    }
}
