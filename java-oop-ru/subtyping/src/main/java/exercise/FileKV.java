package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileKV implements KeyValueStorage{
    private String path;
    private Map<String, String> map = new HashMap<>();
    private String mapToStr;

    public FileKV(String path, Map<String, String> initial) {
        this.path = path;
        this.map.putAll(initial);
        mapToStr = Utils.serialize(map);
        Utils.writeFile(path, mapToStr);
    }

    @Override
    public void set(String key, String value) {
        map.put(key, value);
        String mapToStr = Utils.serialize(map);
        Utils.writeFile(path, mapToStr);
    }

    @Override
    public void unset(String key){
        String content = Utils.readFile(path);
/*        Files.delete(Path.of(path));
        Files.createFile(Path.of(path));*/
        map = Utils.unserialize(content);
        map.remove(key);
        Utils.writeFile(path, "");
        String mapToStr = Utils.serialize(map);
        Utils.writeFile(path, mapToStr);
    }

    @Override
    public String get(String key, String defaultValue) {
        String content = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(content);
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(map);
    }
}
