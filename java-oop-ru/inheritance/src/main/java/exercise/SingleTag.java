package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleTag extends Tag{

    public SingleTag(String tagName, Map<String, String> tagAttributes) {
        super(tagName, tagAttributes);
    }

    @Override
    public String toString() {
        Map<String,String> map = new HashMap<>(tagAttributes);
/*        String strAttributes = String.valueOf(map.entrySet()
                .stream()
                .map(entry -> ("\"" +  entry.getKey() + "\"=\"" + entry.getValue() + "\""))
                .reduce((str1, str2) -> str1 + " " + str2));*/
        String strAttributes = map.entrySet()
                .stream()
                .map(entry -> (entry.getKey() + "=\"" + entry.getValue() + "\""))
                .collect(Collectors.joining(" "));
//                .reduce((str1, str2) -> str1 + " " + str2)
//                .map(Object::toString)
//                .toString();
        strAttributes = strAttributes.length() == 0 ? "" : " " + strAttributes;
        //Tag img = new SingleTag("img", Map.of("class", "v-10", "id", "wop"));
        // "<img class="v-10" id="wop">
        return "<" + tagName + strAttributes +">";

    }
}
