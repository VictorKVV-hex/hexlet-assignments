package exercise;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("two", "own");
        Map<String, Object> data2 = new HashMap<>();
        data2.put("one", "eon");

        Map<String, String> result = App.genDiff(data1, data2);
        System.out.println(result); //=> {one=added, two=deleted}

    }
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        LinkedHashMap<String, String> UnsortedResult =new LinkedHashMap<>();
        Map<String, Object> mapAll = new HashMap<>(data1);
        mapAll.putAll(data2);
        mapAll.forEach((k2, v2) -> UnsortedResult.put(k2, App.valueOfMap(data1,data2,k2,v2)));
/*      //==============// Через for, метод работает //===============
        for ( Map.Entry< String , Object > entry : mapAll.entrySet() ) {
            result.put(
                    entry.getKey() ,
                    App.valueOfMap(data1,data2,entry.getKey(),entry.getValue() )
            );
        }
        //===========================================================//*/
        LinkedHashMap sortedResult = UnsortedResult.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
    return sortedResult;
    }
    public static String valueOfMap(Map<String, Object> data1, Map<String, Object> data2, String key, Object value) {
        String Ret = null;
        boolean isEquals = false;
        boolean isKey1Exists = data1.containsKey(key);
        boolean isKey2Exists = data2.containsKey(key);
        if (isKey1Exists && isKey2Exists) {
            isEquals = data1.get(key).equals(data2.get(key));
        }
        if (!isKey1Exists && isKey2Exists) {
            Ret = "added";
        } else if (isKey1Exists && !isKey2Exists) {
            Ret = "deleted";
        } else if (isKey1Exists && isKey2Exists && !isEquals) {
            Ret = "changed";
        } else if (isKey1Exists && isKey2Exists && isEquals) {
            Ret = "unchanged";
        }
        return Ret;
    }
}