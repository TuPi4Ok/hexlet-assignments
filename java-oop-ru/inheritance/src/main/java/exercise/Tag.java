package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
class Tag {
    private String name;
    private Map<String, String> attributes = new LinkedHashMap<>();
    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes.putAll(attributes);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String toString() {
        String result = "<" + this.getName() + " ";
        for (Map.Entry<String, String> item : this.getAttributes().entrySet()) {
            result += item.getKey() + "=\"" + item.getValue() +"\" ";
        }
        return result.substring(0, result.length() - 1) + ">";
    }
}
// END
