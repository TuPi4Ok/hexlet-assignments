package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String letters, String word) {
        letters = letters.toLowerCase();
        word = word.toLowerCase();

        List<String> listLetters = new ArrayList<>();
        List<String> listWord = new ArrayList<>();

        for (int i = 0; i < letters.length(); i++){
            listLetters.add(letters.substring(i,i+1));
        }

        for (int i = 0; i < word.length(); i++){
            listWord.add(word.substring(i,i+1));
        }

        for (int i = 0; i < word.length(); i++){
            var letter = listWord.get(i);
            if (listLetters.indexOf(letter) == -1)
                return  false;
            else {
                listLetters.remove(letter);
            }

        }
        return true;
    }
}
//END
