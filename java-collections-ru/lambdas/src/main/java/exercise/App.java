package exercise;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        System.out.println("test app");
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"}
        };
        System.out.println(Arrays.deepToString(image));
        System.out.println(Arrays.deepToString(enlargeArrayImage(image)));
    }

//    public static String[][] enlargeArrayImageMy(String[][] image) {
//        return Arrays.stream(image)
//                .map(rows -> Arrays.stream(rows)
//                        .flatMap(items -> Arrays.stream(new String[] {items, items}))
//                        .toArray(String[]::new));
//
//    }
//            return Arrays.stream(image)
//            .map(rows -> Arrays.stream(rows)
//            .flatMap(new Function<String, Stream<?>>() {
//                @Override
//                public Stream<?> apply(String s) {
//                    return Arrays.stream(new String[] {s, s});
//                }
//            })
//            .toArray(String[]::new));
//    }

    public static String[] duplicate(String[] items) {
        return Arrays.stream(items)
                .flatMap(item -> Arrays.stream(new String[] {item, item}))
                .toArray(String[]::new);
    }

    public static String[][] enlargeArrayImage(String[][] image) {

        String[][] horizontalyLine = Arrays.stream(image)
                .map(App::duplicate)
                .toArray(String[][]::new);

        return Arrays.stream(horizontalyLine)
                .flatMap(item -> Arrays.stream(new String[][] {item, item}))
                .toArray(size -> new String[size][]);
//                .toArray(String[][]::new);
    }

}
