package exercise;

import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static List<String> buildApartmentsList(List<Home> list, int n) {
        return list.stream()
            .sorted((o1, o2) -> o1.compareTo(o2))
            .limit(n)
            .map(Home::toString)
            .collect(Collectors.toList());
    }
}