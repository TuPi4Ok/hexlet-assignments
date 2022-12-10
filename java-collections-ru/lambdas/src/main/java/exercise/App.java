package exercise;

import java.util.Arrays;

// BEGIN
class App {
    private static String[] dabble (String[] str) {
        String[] result = new String[str.length * 2];
        int index = 0;
        for (String s : str) {
            result[index] = s;
            index++;
            result[index] = s;
            index++;
        }
        return result;
    }
    private static String[][] dabbleForstolb (String[][] str){
        String[][] result = new String [str.length * 2][str[0].length];
        int index = 0;
        for (String[] s : str) {
            result[index] = s;
            index++;
            result[index] = s;
            index++;
        }
        return result;
    }
    public static String[][] enlargeArrayImage (String[][] start) {
        String[][] firstStrep = Arrays.stream(start).toList().stream()
                .map(App::dabble)
                .toArray(String[][]::new);
        return dabbleForstolb(firstStrep);
    }
}
// END
