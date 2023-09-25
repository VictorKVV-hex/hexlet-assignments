package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sorter {
    public static void main(String[] args) {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> men = Sorter.takeOldestMans(users);
        System.out.println(men);
    }

    public static List<String> takeOldestMans(List<Map<String, String>> users) {

//        return users.stream()
//                .filter(gender -> gender.get("gender").equals("male"))
//                .sorted((name1, name2) -> name1.get("birthday").compareTo(name2.get("birthday")))
//                .map(name1 -> name1.get("name"))
//                .collect(Collectors.toList());
        return users.stream()
                .filter(new Predicate<Map<String, String>>() {
                    @Override
                    public boolean test(Map<String, String> bookMap) {
                        return bookMap.get("gender").equals("male");
                    }
                })
                .sorted(new Comparator<Map<String, String>>() {
                    @Override
                    public int compare(Map<String, String> o1, Map<String, String> o2) {
                        return o1.get("birthday").compareTo(o2.get("birthday"));
                    }
                })
                .map(name1 -> name1.get("name"))
                .collect(Collectors.toList());
    }
}

