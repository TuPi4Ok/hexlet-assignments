package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage bd) {
        for(Map.Entry<String, String> item : bd.toMap().entrySet()) {
            String value = item.getValue();
            String key = item.getKey();
            bd.set(value, key);
            bd.unset(key);
        }
    }
}
// END
