import java.util.*;

public interface Node {
    String getName();

    Map<String, List<Node>> getRelationships();
}
