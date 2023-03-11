import java.time.LocalDate;
import java.util.*;

public class Person implements Node, Comparable<Person> {
    protected Map<String, List<Node>> relationships;
    private String name;
    private LocalDate birthDate;
    public Person(String name, LocalDate birthDate) { //constructor
        this.name = name;
        this.birthDate = birthDate;
        this.relationships = new HashMap<>();
    }
    //getters and setters
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Map<String, List<Node>> getRelationships() {
        return relationships;
    }

    public void setRelationships(Map<String, List<Node>> relationships) {
        this.relationships = relationships;
    }
    //adding relationships
    public void addRelationships(String relationshipType, Node node) {
        if (!relationships.containsKey(relationshipType)) {
            relationships.put(relationshipType, new ArrayList<Node>());
        }
        relationships.get(relationshipType).add(node);
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo((o.getName()));
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
        return "{Person name: " + getName() + ", " + "Birth date: " + getBirthDate() + ", " + "Relationships: " + sb + "}";
    }
}