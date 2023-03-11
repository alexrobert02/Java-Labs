import java.util.*;

public class Company implements Node, Comparable<Company> {
    private String name;
    private Map<String, List<Node>> relationships;
    public Company(String name) { //constructor
        setName(name);
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

    @Override
    public Map<String, List<Node>> getRelationships() {
        return relationships;
    }

    public void addRelationships(String relationshipType, Node node) { //adding relationships
        List<Node> nodes = this.relationships.computeIfAbsent(relationshipType, k -> new ArrayList<>()); //checking if we already have that relationshipType
        nodes.add(node);
    }

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.getName());
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
        return "{Company name: " + getName() + ", " + "Employees: " + sb + "}";
    }
}
