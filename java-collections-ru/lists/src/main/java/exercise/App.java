package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// BEGIN
public class App {
    public static void main(String[] args) {
        System.out.println(App.scrabble("rkqodlw", "woRld"));
        System.out.println(App.scrabble("begsdhhtsexoult", "Hexlet"));
        System.out.println(App.scrabble("thlxertwq", "hexlet"));
        System.out.println(App.scrabble("jvayu", "java"));
        System.out.println(App.scrabble("", "java"));
        System.out.println(App.scrabble("avjafff", "JaVa")); // true
        System.out.println(App.scrabble("", "hexlet")); // false
    }

    public static boolean scrabble(String str, String word) {
        List<String> strList = new ArrayList<String>(Arrays.asList(str.toUpperCase().split("")));
        List<String> wordList = new ArrayList<>(Arrays.asList(word.toUpperCase().split("")));
        strList.retainAll(wordList);
        Map<Integer, String> newStrListAswordList = new HashMap<>();
        for (String charOfWord : wordList) {
            Boolean isMatch = false;
            for (int i = 0; i < strList.size(); i++) {
                String charOfStr = strList.get(i);
                if (charOfWord.equals(charOfStr)) {
                    if (!newStrListAswordList.containsKey(i)) {
                        isMatch = true;
                        newStrListAswordList.put(i, charOfStr);
                        break;
                    }
                }
            }
            if (isMatch == false) {
                return false;
            }
        }
        return newStrListAswordList.size() == wordList.size();
    }
}
//END
