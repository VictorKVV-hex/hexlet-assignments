package exercise;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Map;

public abstract class Tag {
    String tagName;
    Map<String,String> tagAttributes;

    public Tag(String tagName, Map<String, String> tagAttributes) {
        this.tagName = tagName;
        this.tagAttributes = tagAttributes;
    }

    public String toSingleString() {
//        Map<String,String> map = new HashMap<>(tagAttributes);
        String strAttributes = tagAttributes.entrySet()
                .stream()
                .map(entry -> (entry.getKey() + "=\"" + entry.getValue() + "\""))
                .collect(Collectors.joining(" "));
        strAttributes = strAttributes.length() == 0 ? "" : " " + strAttributes;
        return "<" + tagName + strAttributes +">";
    }

    public abstract String toString();
}