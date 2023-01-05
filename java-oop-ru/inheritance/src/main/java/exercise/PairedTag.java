package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag{
    String body;
    List <Tag> singleList = new ArrayList<>();

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> singleList) {
        super(name, attributes);
        this.body = body;
        this.singleList.addAll(singleList);
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += this.body;
        for(Tag item : this.singleList) {
            result += item.toString();
        }
        return result + "</" + getName() + ">";
    }
}
// END
