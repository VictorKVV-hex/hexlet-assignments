package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class PairedTag extends Tag{
    String bodyTag;
    List<Tag> childrenTag;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String bodyTag, List<Tag> childrenTag) {
        super(tagName, tagAttributes);
        this.bodyTag = bodyTag;
        this.childrenTag = childrenTag;
    }

    @Override
    public String toString() {
        String strAttributes = tagAttributes.entrySet()
                .stream()
                .map(entry -> (entry.getKey() + "=\"" + entry.getValue() + "\""))
                .collect(Collectors.joining(" "));
        strAttributes = strAttributes.length() == 0 ? "" : " " + strAttributes;
        String childrenStringTag = childrenTag.stream().map(Tag::toSingleString).collect(Collectors.joining(""));

        // "<div class="y-5"><br id="s"><hr class="a-5"></div>"
        return "<" + tagName + strAttributes + ">" + bodyTag + childrenStringTag + "</" + tagName + ">";
    }
}
