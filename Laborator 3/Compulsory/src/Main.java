import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Alex");
        Person p2 = new Person("Andra");
        Company c1 = new Company("Amazon");
        Company c2 = new Company("Bitdefender");
        List<Node> nodes = new ArrayList<>();
        nodes.add(p1);
        nodes.add(c1);
        nodes.add(p2);
        nodes.add(c2);
        /**
         * functie lambda care creeaza criteriul de sortare a nodurilor(dupa nume)
         */
        Collections.sort(nodes, (node1, node2) -> node1.getName().compareTo(node2.getName()));
        System.out.println(nodes);
    }
}