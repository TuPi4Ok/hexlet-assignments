package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {

    private static boolean existKey (Map.Entry<String, Object> m1, Map<String, Object> map2) {
        for (Map.Entry<String, Object> m2 : map2.entrySet()) {
            if (m2.getKey().equals(m1.getKey()))
                return true;
        }
        return false;
    }
    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map <String, String> result = new LinkedHashMap<>();

        for (Map.Entry<String, Object> m1 : map1.entrySet()) {
            if (existKey(m1, map2)){
                for (Map.Entry<String, Object> m2 : map2.entrySet()) {
                    if(m1.getKey().equals(m2.getKey()) && m1.getValue().equals(m2.getValue()))
                        result.put(m1.getKey(), "unchanged");
                    else if(m1.getKey().equals(m2.getKey()) && !m1.getValue().equals(m2.getValue()))
                        result.put(m1.getKey(), "changed");
                }
            }
            else {
                result.put(m1.getKey(), "deleted");
            }
        }

        for (Map.Entry<String, Object> m2 : map2.entrySet()) {
            if (!existKey(m2, map1))
                result.put(m2.getKey(), "added");
        }
        return result;
    }
}
//END
