package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

public class App {
    public static void main(String[] args) {

    }

    public static List<Map<String, String>>findWhere(List<Map<String, String>> listOfBooks, Map<String, String> booksWhere) {
        List<Map<String, String>> newBooks = new ArrayList<>();
        for (int i = 0; i < listOfBooks.size(); i++) {
            Map<String, String> book = listOfBooks.get(i);
            if (App.isContains( book, booksWhere)) {
                newBooks.add(book);
            }

        }
        return newBooks;
    }

    public static boolean isContains(Map<String, String> haystack, Map<String, String> needles) {
        Integer countTrue = 0;
        for (String key : haystack.keySet()) {
            if (needles.containsKey(key) && needles.get(key).equals(haystack.get(key))) {
                countTrue ++;
            }
        }
        if (countTrue == needles.size()) {
            return true;
        }
        return false;
    }
}
// BEGIN

//END
