package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    private static boolean isResuit(Map<String, String> oneMap, Map<String, String> map) {
        for (Map.Entry<String, String> map1: map.entrySet()) {
            for (Map.Entry<String, String> map2: oneMap.entrySet()) {
                if(map1.getKey() == map2.getKey())
                    if(map1.getValue() != map2.getValue())
                        return false;
            }
        }
        return true;
    }
    public static List<Map<String, String>> findWhere(List<Map<String, String>> list, Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            Map<String, String> oneMap = list.get(i);
            if(isResuit(oneMap, map))
                result.add(oneMap);
        }
        return result;
    }
}
//END
