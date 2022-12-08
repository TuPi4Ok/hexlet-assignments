package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {

    private static int count(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }

    public static Map getWordCount(String str) {
        String[] keys = str.split(" ");
        Map<String, Integer> words = new HashMap<>();
        if (str.length() > 0){
            for ( int i = 0; i < keys.length; i++){
                words.put(keys[i], count(str, keys[i]));
            }
        }

        return words;
    }

    public static String toString (Map<String, Integer> words) {
        boolean flag = true;
        String result = "{";
        for (String s: words.keySet()) {
            if (flag){
                result = result + "\n";
                flag = false;
            }
            int count = words.get(s);
            result = result + "  " + s + ": " + count + "\n";
        }
        result = result + "}";
        return result;
    }
}
//END
