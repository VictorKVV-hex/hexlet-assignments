package exercise;

import java.util.*;

public class App {
// BEGIN
    public static void main(String[] args) {
//        App.getWordCount("java is the best programming language java");
//        String sentence = "word text dog apple word apple word";
        String sentence = "";
        Map<String, Integer> wordsCount = App.getWordCount(sentence);
        String result = App.toString(wordsCount);
        System.out.println(result);
    }
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String,Integer> wordCountMap = new LinkedHashMap<>();
        List<String> words = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        if (sentence.equals("")) {
            words.clear();
        }
        for (String word : words) {
            int count=Collections.frequency(words, word);
            wordCountMap.put(word, count);
        }
        return wordCountMap;
    }

    public static String toString(Map<String, Integer> wordsFromSentence) {
        var result = new StringBuilder();
        result.append("{");
        if (!wordsFromSentence.isEmpty()) {
            result.append("\n");
        }
        for (Map.Entry<String, Integer> word: wordsFromSentence.entrySet()) {
            result.append("  " + word.getKey() + ": " + word.getValue() + "\n");
        }
        result.append("}");
        return result.toString();
    }
//END
}