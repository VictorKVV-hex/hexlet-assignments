package exercise;

import java.io.IOException;
import java.util.Map;

interface KeyValueStorage {
    void set(String key, String value);
    void unset(String key) throws IOException;
    String get(String key, String defaultValue);
    Map<String, String> toMap();
}
