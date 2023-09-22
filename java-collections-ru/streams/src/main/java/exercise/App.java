package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static void main(String[] args) {
        String[] emails = {
                "info@gmail.com",
                "info@yandex.ru",
                "mk@host.com",
                "support@hexlet.io",
                "info@hotmail.com",
                "support.yandex.ru@host.com"
        };

        List<String> emailsList = Arrays.asList(emails);
        System.out.println(App.getCountOfFreeEmails(emailsList)); // 3
    }
    public static Integer getCountOfFreeEmails(List<String> emailList) {
//        String str0 = "info@gmail.com";
//        String str1 = str0.substring(str0.indexOf("@"));
//        List<String> newList = emailList.stream()
//                .filter(str -> str.substring(str.indexOf("@")+1).equals("gmail.com"))
//                .collect(Collectors.toList());
        int countEmail = (int) emailList.stream()
                .filter(str -> str.substring(str.indexOf("@")+1).equals("gmail.com")
                        || str.substring(str.indexOf("@")+1).equals("yandex.ru")
                        || str.substring(str.indexOf("@")+1).equals("hotmail.com"))
                .count();
        return countEmail;
    }
}
// END
