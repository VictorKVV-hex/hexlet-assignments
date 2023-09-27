package exercise;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class App {
    private static String data1;
    private static String data2;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }
/*    public static void beforeAll() throws Exception {
        data1 = readFixture("s1.conf");
        data2 = readFixture("s2.conf");
    }*/
    public static void main(String[] args) throws Exception {
        data1 = readFixture("s1.conf");
        data2 = readFixture("s2.conf");
//        String result1 = App.getForwardedVariables(data1);
        String result2 = App.getForwardedVariables(data2);
    }

    public static String getForwardedVariables(String configStr){

        List<String> configList = new ArrayList<>(Arrays.asList(configStr.split("\\[(.*?)\\]")));

        String strSumConfig = configList.stream()
                .filter(str -> !str.isEmpty())
                .filter(str -> str.startsWith("\nenvironment"))
                .map(str -> str.substring(str.indexOf("environment=\"")+13, str.indexOf("\"", 14)))
                .map(str -> str.replaceAll("\"",""))
                .map(str -> str.replaceAll("\\s",""))
                .flatMap(item -> Arrays.stream(item.split(","))
                        .filter(str -> str.startsWith("X_FORWARDED_")))
                .map(str -> str.replaceAll("X_FORWARDED_",""))
                .map(str -> str.replaceAll(",$",""))
                .collect(Collectors.joining(","));
//                .toString();
//                .toList();
/*        for(String lineEnvironment: configList) {
            int envNum = lineEnvironment.indexOf("environment=\"") + 13;
            int slashNum = lineEnvironment.indexOf("\"",14);
//            String value = lineEnvironment.substring(lineEnvironment.indexOf("environment=\"") + 13, lineEnvironment.indexOf("\"",14));
            String value = lineEnvironment.substring(envNum, slashNum);
//            String v1 = lineEnvironment.substring(1 + 1, 6).toString();
            System.out.println(value);
        }*/

        return strSumConfig;
    }

}

