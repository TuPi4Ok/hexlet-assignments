package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {
    Map<String, String> dictionary;

    public InMemoryKV(Map<String, String> dictionary) {
        this.dictionary = new HashMap<>();
        this.dictionary.putAll(dictionary);
    }

    @Override
    public void set(String key, String value) {
        this.dictionary.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.dictionary.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.dictionary.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> newMap = new HashMap<>();
        newMap.putAll(this.dictionary);
        return newMap;
    }
}
// END
