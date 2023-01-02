package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    String path;

    public FileKV(String path, Map<String, String> defaultMap) {
        this.path = path;
        Utils.writeFile(path, Utils.serialize(defaultMap));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> currentMap = new HashMap<>();
        currentMap.putAll(Utils.unserialize(Utils.readFile(this.path)));
        currentMap.put(key, value);
        Utils.writeFile(path, Utils.serialize(currentMap));
    }

    @Override
    public void unset(String key) {
        Map<String, String> currentMap = new HashMap<>();
        currentMap.putAll(Utils.unserialize(Utils.readFile(this.path)));
        currentMap.remove(key);
        Utils.writeFile(path, Utils.serialize(currentMap));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> currentMap = new HashMap<>();
        currentMap.putAll(Utils.unserialize(Utils.readFile(this.path)));
        return currentMap.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> currentMap = new HashMap<>();
        currentMap.putAll(Utils.unserialize(Utils.readFile(this.path)));
        return currentMap;
    }
}
// END
