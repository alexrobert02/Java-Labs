import java.time.LocalDate;
import java.util.*;

public class Designer extends Person {
    private String style;

    public Designer(String name, LocalDate birthDate, String style) { //constructor
        super(name, birthDate);
        this.style = style;
    }
    //getters and setters
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() { //building the string for output
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Node>> entry : relationships.entrySet()) { //keeping an entry with both the key and values
            sb.append("[");
            sb.append(entry.getKey()).append(": ");
            List<Node> nodes = entry.getValue();
            for (Node node : nodes) { //we add to sb each value of the entry that corresponds to that key
                sb.append(node.getName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // removing last comma and space
            sb.append("], "); // removing last square bracket, comma and space
        }
        sb.delete(sb.length() - 2, sb.length()); //remove last comma and space
        return "{Designer name: " + getName() + ", " + "Birth date: " + getBirthDate() + ", " + "Style: " + getStyle() + ", " + "Relationships: " + sb + "}";
    }
}
