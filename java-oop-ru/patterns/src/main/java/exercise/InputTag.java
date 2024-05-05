package exercise;

public class InputTag implements TagInterface{
    String type;
    String value;
    public InputTag() {

    }

    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        String str = "<input type=\"submit\" value=\"Save\">";
        return String.format("<input type=\"%s\" value=\"%s\">", type, value);
    }
}
