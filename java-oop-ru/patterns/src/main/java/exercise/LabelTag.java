package exercise;

public class LabelTag implements TagInterface{
    String type;
    private TagInterface tag;

    public LabelTag(String type, TagInterface tag) {
        this.type = type;
        this.tag = tag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", type, tag.render());
    }
}