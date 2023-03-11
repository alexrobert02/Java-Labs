import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Programmer extends Person {
    private String programmingLanguage;

    public Programmer(String name, LocalDate birthDate, String programmingLanguage) {
        super(name, birthDate);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
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
        return "{Programmer name: " + getName() + ", " + "Birth date: " + getBirthDate() + ", " + "Programming language: " + getProgrammingLanguage() + ", " + "Relationships: " + sb + "}";
    }
}
