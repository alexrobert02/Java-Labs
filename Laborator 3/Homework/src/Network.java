import java.util.*;

public class Network {
    private List<Node> nodes;

    public Network() {
        this.nodes = new ArrayList<>();
    }
    public Network(List<Node> nodes) { //constructor
        this.nodes = nodes;
    }
    //getters and setters
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    public void addNode(Node node) { //adding a node to the list
        nodes.add(node);
    }

    public int getNodeImportance(Node node) {
        int importance = node.getRelationships().size(); //counting the nodes that are connected to the node
        for (Node otherNode : nodes) {
            Map<String, List<Node>> relationships = otherNode.getRelationships(); //for each node in nodes, we keep a map with the relationships
            if (relationships.containsValue(List.of(node))) { //checking if the nodes are connected the other way around.
                importance++;
            }
        }
        return importance;
    }

    @Override
    public String toString() { //building the string for output
        StringBuilder sb = new StringBuilder();
        sb.append("Network:\n");
        Collections.sort(nodes, (n1, n2) -> Integer.compare(getNodeImportance(n2), getNodeImportance(n1))); //lambda function to sort the nodes by importance.
        for (Node node : nodes) {
            sb.append(node.toString() + " - Importance: " + getNodeImportance(node) + "\n");
        }
        return sb.toString();
    }
}











